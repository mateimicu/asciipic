"""Parser endpoint for the AsciiPic API."""
import cherrypy

from asciipic.api import base as base_api


class ParserEndpoint(base_api.Resource):

    """This API will parse the CLI command and return a list with endpoints"""

    # Whether this application should be available for clients
    exposed = True

    # pylint: disable=no-self-use
    @cherrypy.tools.json_out()
    def GET(self, **kwargs):
        """Parse the command and return a JSON.

        The format will be :
            {   "cmd": command,
                "actions": [
                    {
                        "type": "start"
                        "endpoint": "..",
                        "method: "POST"
                        "data": { .. }
                    },

                    {
                        "type": "pipe"
                        "endpoint": "..",
                        "method: "POST"
                        "data": { .. }
                    },

                    {
                        "type": "and"
                        "endpoint": "..",
                        "method: "GET"
                        "data": { .. }
                    },
                    {
                        "type": "or"
                        "endpoint": "..",
                        "method: "GET"
                        "data": { .. }
                    },
                    {
                        "type": "end"
                        "endpoint": "..",
                        "method: "GET"
                        "data": { .. }
                    },

                ]
            }
        """
        # Return some mock data for now
        command = kwargs.pop("command", "")
        return {
            "cmd": command,
            "actions": [
                {
                    "type": "end",
                    "url": "/api/version",
                    "method": "GET",
                    "data": None
                }
            ]

        }
