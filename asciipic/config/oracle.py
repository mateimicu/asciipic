"""Config options available for the asciipic oracle connection."""
import os

from oslo_config import cfg

from asciipic.config import base as conf_base
from asciipic.common import constant


class AsciipicOracleOptions(conf_base.Options):

    """Config options available for the asciipic oracle connection."""

    def __init__(self, config):
        super(AsciipicOracleOptions, self).__init__(config, group="oracle")
        self._options = [
            cfg.StrOpt(
                "host", default=os.getenv(
                    constant.ORACLE_HOST, constant.DEFAULT_ORACLE_HOST),
                help="The IP address or the host name of the oracle server."),
            cfg.IntOpt(
                "port", default=int(os.getenv(
                    constant.ORACLE_PORT, constant.DEFAULT_ORACLE_PORT)),
                required=True, help="The port that should be used"),
            cfg.StrOpt(
                "username", default=os.getenv(
                    constant.ORACLE_USERNAME,
                    constant.DEFAULT_ORACLE_USERNAME),
                required=True,
                help="The username to use for the DB connection"),
            cfg.StrOpt(
                "password", default=os.getenv(
                    constant.ORACLE_PASSWORD,
                    constant.DEFAULT_ORACLE_PASSWORD),
                required=True,
                help="The password to use for the DB connection"),
            cfg.StrOpt(
                "dbname", default=os.getenv(
                    constant.ORACLE_DBNAME, constant.DEFAULT_ORACLE_DBNAME),
                required=True,
                help="The username to use for the DB connection"),
        ]

    def register(self):
        """Register the current options to the global ConfigOpts object."""
        group = cfg.OptGroup(self.group_name,
                             title='Asciipic Oracle Options')
        self._config.register_group(group)
        self._config.register_opts(self._options, group=group)

    def list(self):
        """Return a list which contains all the available options."""
        return self._options
