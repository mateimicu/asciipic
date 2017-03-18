"""The module contains the / endpoint object for Asciipic API."""

import os

import cherrypy

from asciipic.api import base as api_base
from asciipic import config as asciipic_config

CONFIG = asciipic_config.CONFIG


class Root(api_base.BaseAPI):

    """The / endpoint for the Asciipic API."""

    resources = []

    @classmethod
    def config(cls):
        """Prepare the configurations."""
        return {
            'global': {
                'server.socket_host': CONFIG.api.host,
                'server.socket_port': CONFIG.api.port,
                'environment': CONFIG.api.environment,
                'log.screen': True,
                'log.error_file': os.path.join(CONFIG.log_dir or "",
                                               "asciipic-api-error.log"),
                'server.thread_pool': CONFIG.api.thread_pool,
            },
            '/': {
                'request.dispatch': cherrypy.dispatch.MethodDispatcher()
            }
        }
