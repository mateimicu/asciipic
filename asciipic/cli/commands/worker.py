"""Worker CLI."""
import os
import signal

from oslo_log import log as logging

from asciipic.cli import base as cli_base
from asciipic.common import constant
from asciipic.common import exception
from asciipic import config as asciipic_config
from asciipic.worker import worker as asciipic_worker


LOG = logging.getLogger(__name__)
CONFIG = asciipic_config.CONFIG


class Start(cli_base.Command):

    """Start the Asciipic API."""

    def setup(self):
        """Extend the parser configuration in order to expose this command."""
        parser = self._parser.add_parser("start",
                                         help="Start the Asciipic worker.")
        parser.add_argument("--redis_port", dest="redis_port", type=int,
                            default=CONFIG.worker.redis_port,
                            help="The port that should be used by"
                            " the current worker.")
        parser.add_argument("--redis_host", dest="redis_host", type=str,
                            default=CONFIG.worker.redis_host,
                            help="The IP address or the host name of the "
                            "server.")
        parser.add_argument("--redis_database", dest="redis_database",
                            type=str, default=CONFIG.worker.redis_database,
                            help="The redis database to use.")
        parser.add_argument("--redis_password", dest="redis_password",
                            type=str, default=CONFIG.worker.redis_password,
                            help="The redis password to use.")
        parser.add_argument("--name", dest="name",
                            type=str, default=CONFIG.worker.name,
                            help="The worker name to use.",)
        parser.add_argument("--queues", nargs="+", help="Queues to subscribe.",
                            default=CONFIG.worker.queues)
        parser.set_defaults(work=self.run)

    def _work(self):
        """Start the Asciipic Worker."""
        pid = os.getpid()
        with open(constant.PID_WORKER_TMP_FILE, "w") as file_handle:
            file_handle.write(str(pid))
        worker = asciipic_worker.Worker(
            queues=self.args.queues,
            name=self.args.name,
            redis_host=self.args.redis_host,
            redis_port=self.args.redis_port,
            redis_database=self.args.redis_database,
            redis_password=self.args.redis_password)

        # Start the worker
        worker.work()


class Stop(cli_base.Command):

    """Stop the Asciipic Worker."""

    def setup(self):
        """Extend the parser configuration in order to expose this command."""
        parser = self._parser.add_parser(
            "stop", help="Stop the Asciipic Worker.")
        parser.set_defaults(work=self.run)

    def _work(self):
        """Stop the Asciipic Worker."""
        pid = None
        try:
            with open(constant.PID_WORKER_TMP_FILE, "r") as file_handle:
                pid = int(file_handle.read().strip())
        except (ValueError, OSError) as exc:
            LOG.error("Failed to get server PID: %s", exc)
            raise exception.NotFound("Failed to get server PID.")

        try:
            os.kill(pid, signal.SIGTERM)
        except OSError as exc:
            LOG.error("Failed to shutdown the server: %s", exc)
            return False

        return True


class Worker(cli_base.Group):

    """Group for all the available worker actions."""

    commands = [
        (Start, "actions"),
        (Stop, "actions")]

    def setup(self):
        """Extend the parser configuration in order to expose this command."""
        parser = self._parser.add_parser(
            "worker",
            help="Operations related to the Asciipic worker (start/stop).")

        actions = parser.add_subparsers()
        self._register_parser("actions", actions)
