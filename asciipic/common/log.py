"""Logging utility."""
from oslo_config import cfg
from oslo_log import log as logging

from asciipic.common import constant


def prepare_log_module():
    """Prepare log module."""
    logging.register_options(cfg.CONF)
    logging.setup(cfg.CONF, constant.LOG_DOMAIN)
