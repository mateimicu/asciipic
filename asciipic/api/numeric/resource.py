"""Arias API endpoint for spider management."""

import cherrypy
from oslo_log import log as logging

from asciipic.api import base as base_api


LOG = logging.getLogger(__name__)


class AddNumberds(base_api.Resource):
    """Add two number"""

    exposed = True

    @staticmethod
    @cherrypy.tools.json_out()
    def GET(first, second):
        """Compute sum."""

        response = {"sum": float(first) + float(second)}
        LOG.info(response)
        return response


class SubtractNumbers(base_api.Resource):
    """Subtract two number"""

    exposed = True

    @staticmethod
    @cherrypy.tools.json_out()
    def GET(first, second):
        """Compute the diff."""

        response = {"dif": float(first) - float(second)}
        LOG.info(response)
        return response
