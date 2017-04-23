"""Config options available for the journalize setup."""

from oslo_config import cfg

from asciipic.config import base as conf_base


class AsciipicJournalizeOptions(conf_base.Options):

    """Config options available for the journalize setup."""

    def __init__(self, config):
        super(AsciipicJournalizeOptions, self).__init__(config,
                                                        group="journalize")
        self._options = [
            cfg.StrOpt(
                "journalize_host",
                default="/statistics.html",
                help="The IP address or the host name of the server "
                     "of journalize service."),
        ]

    def register(self):
        """Register the current options to the global ConfigOpts object."""
        group = cfg.OptGroup(self.group_name,
                             title='Asciipic journalize Options')
        self._config.register_group(group)
        self._config.register_opts(self._options, group=group)

    def list(self):
        """Return a list which contains all the available options."""
        return self._options
