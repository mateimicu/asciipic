"""The module contains the / endpoint object for Asciipic API."""

import os

import cherrypy
from oslo_log import log as logging

from asciipic.api import base as api_base
from asciipic.api import parser
from asciipic.api import api_endpoint
from asciipic import config as asciipic_config
from asciipic.common import util

CONFIG = asciipic_config.CONFIG
LOG = logging.getLogger(__name__)


class Root(api_base.BaseAPI):

    """The / endpoint for the Asciipic API."""

    resources = [
        ("parse", parser.ParserEndpoint),
        ("api", api_endpoint.APIEndpoint),
    ]

    @classmethod
    def config(cls, host=CONFIG.api.host, port=CONFIG.api.port):
        """Prepare the configurations."""
        return {
            'global': {
                'server.socket_host': host,
                'server.socket_port': port,
                'environment': CONFIG.api.environment,
                'log.screen': True,
                'log.error_file': os.path.join(CONFIG.log_dir or "",
                                               "asciipic-api-error.log"),
                'server.thread_pool': CONFIG.api.thread_pool,
            },
            '/': {
                'request.dispatch': cherrypy.dispatch.MethodDispatcher()
            },
            '/js': {
                'tools.staticdir.on': True,
                'tools.staticdir.dir': util.get_resource_path("web/js")
            },
            '/css': {
                'tools.staticdir.on': True,
                'tools.staticdir.dir': util.get_resource_path("web/css")
            },
            '/img': {
                'tools.staticdir.on': True,
                'tools.staticdir.dir': util.get_resource_path("web/img")
            }
        }

    def GET(self):
        """Return the index page."""
        with open(util.get_resource_path("web/index.html")) as tmp:
            data = tmp.read()
            try:
                data = data.format(journalize_host=CONFIG.journalize.journalize_host)
            except KeyError:
                LOG.warn("Cound not add the journalize_host.")

            return data

