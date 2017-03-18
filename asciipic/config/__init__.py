"""Init file for config sections."""

import os

from oslo_config import cfg
from oslo_log import log as logging

from asciipic.config import factory
from asciipic import version

CONFIG = cfg.ConfigOpts()

logging.register_options(CONFIG)
for option_class in factory.get_options():
    option_class(CONFIG).register()

_DEFAULT_CONFIG_FILES = [
    config_file for config_file in ("asciipic.conf",
                                    "etc/asciipic/cars_scrap.conf",
                                    "/etc/asciipic/cars_scrap.conf")
    if os.path.isfile(config_file)
]

if _DEFAULT_CONFIG_FILES:
    CONFIG([], project='asciipic', version=version.get_version(),
           default_config_files=_DEFAULT_CONFIG_FILES)
