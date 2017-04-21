"""Version EndPoint."""
import cherrypy

from asciipic.api import base as base_api


class Version(base_api.Resource):
    """Get the current version."""

    @staticmethod
    @cherrypy.tools.json_out()
    def GET():
        return {
            "exitcode": True,
            "data": {
                "version": "0.0.1",
            },
            "visual": "version 0.0.1"
        }
