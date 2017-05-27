"""Config options available for the worker setup."""

from oslo_config import cfg

from asciipic.config import base as conf_base


class WorkerOptions(conf_base.Options):

    """Config options available for the worker setup."""

    def __init__(self, config):
        super(WorkerOptions, self).__init__(config, group="worker")
        self._options = [
            cfg.StrOpt(
                "redis_host", default="127.0.0.1",
                help="The IP address or the host name of the server."),
            cfg.IntOpt(
                "redis_port", default=6379, required=True,
                help="The port that should be used by the current "
                     "metadata service."),
            cfg.IntOpt(
                "redis_database", default=1, required=True,
                help="The name of the database that should be used."),
            cfg.StrOpt(
                "redis_password", default=None,
                help="Password for the connection."),
            cfg.StrOpt(
                "name", default=None, help="The name of the worker."),
            cfg.MultiStrOpt(
                "queues", default=['high', 'normal', 'low'], required=True,
                help="Queues to listen on."),
        ]

    def register(self):
        """Register the current options to the global ConfigOpts object."""
        group = cfg.OptGroup(self.group_name, title='Worker Options')
        self._config.register_group(group)
        self._config.register_opts(self._options, group=group)

    def list(self):
        """Return a list which contains all the available options."""
        return self._options
