"""Config options available for the asciipic setup."""

from oslo_config import cfg

from asciipic.config import base as conf_base


class AsciipicOptions(conf_base.Options):

    """Config options available for the asciipic setup."""

    def __init__(self, config):
        super(AsciipicOptions, self).__init__(config, group="DEFAULT")
        self._options = []

    def register(self):
        """Register the current options to the global ConfigOpts object."""
        group = cfg.OptGroup(self.group_name, title='Asciipic Options')
        self._config.register_group(group)
        self._config.register_opts(self._options, group=group)

    def list(self):
        """Return a list which contains all the available options."""
        return self._options
