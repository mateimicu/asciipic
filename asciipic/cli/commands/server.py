"""Server CLI."""
import os
import signal

import cherrypy
from oslo_log import log as logging

from asciipic import api as asciipic_api
from asciipic.cli import base as cli_base
from asciipic.common import constant
from asciipic.common import exception
from asciipic import config as asciipic_config
from asciipic.db.oracle import base
from asciipic.db.oracle import init


LOG = logging.getLogger(__name__)
CONFIG = asciipic_config.CONFIG


class Start(cli_base.Command):

    """Start the Asciipic API."""

    def setup(self):
        """Extend the parser configuration in order to expose this command."""
        parser = self._parser.add_parser("start",
                                         help="Start the Asciipic API.")
        parser.add_argument("--port", dest="port", default=CONFIG.api.port,
                            type=int, help="The port that should be used by"
                            " the current metadata service.")

        parser.add_argument("--host", dest="host", default=CONFIG.api.host,
                            type=str, help="The IP address or the host"
                            " name of the server.")
        parser.set_defaults(work=self.run)

    def _work(self):
        """Start the Asciipic API."""

        # TODO(mmicu): Move this to the OracleDBManager
        init.ORACLE_DB.register_base(base.META_BASE)

        pid = os.getpid()
        with open(constant.PID_TMP_FILE, "w") as file_handle:
            file_handle.write(str(pid))
        cherrypy.quickstart(asciipic_api.Root(), "/",
                            asciipic_api.Root.config(host=self.args.host,
                                                     port=self.args.port))


class Stop(cli_base.Command):

    """Stop the Asciipic API."""

    def setup(self):
        """Extend the parser configuration in order to expose this command."""
        parser = self._parser.add_parser("stop", help="Stop the Asciipic API.")
        parser.set_defaults(work=self.run)

    def _work(self):
        """Stop the Asciipic API."""
        pid = None
        try:
            with open(constant.PID_TMP_FILE, "r") as file_handle:
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


class Server(cli_base.Group):

    """Group for all the available server actions."""

    commands = [
        (Start, "actions"),
        (Stop, "actions")]

    def setup(self):
        """Extend the parser configuration in order to expose this command."""
        parser = self._parser.add_parser(
            "server",
            help="Operations related to the Asciipic API (start/stop).")

        actions = parser.add_subparsers()
        self._register_parser("actions", actions)
