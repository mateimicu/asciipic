"""Config options available for the redis database."""
import os

from oslo_config import cfg

from asciipic.config import base as conf_base
from asciipic.common import constant


class RedisOptions(conf_base.Options):

    """Config options available for the asciipic setup."""

    def __init__(self, config):
        super(RedisOptions, self).__init__(config, group="redis")
        self._options = [
            cfg.StrOpt(
                "host", default="127.0.0.1", required=True,
                help="The IP address or the host name of the server."),
            cfg.IntOpt(
                "port", default=6379, required=True,
                help="The port that should be used by the current "
                     "metadata service."),
            cfg.IntOpt(
                "database", default=0, required=True,
                help="The name of the database that should be used."),
            cfg.StrOpt(
                "password", default=None,
                help="Password for the redis connection."),
            cfg.StrOpt(
                "url", default=os.getenv(constant.REDIS_URL_ENV),
                help="Redis URL with all the params."
                     "This option will take precedence over "
                     "other parameters."),
        ]

    def register(self):
        """Register the current options to the global ConfigOpts object."""
        group = cfg.OptGroup(self.group_name, title='Redis Options')
        self._config.register_group(group)
        self._config.register_opts(self._options, group=group)

    def list(self):
        """Return a list which contains all the available options."""
        return self._options
