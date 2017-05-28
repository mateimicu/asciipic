"""API  endpoint for the AsciiPic API."""
import cherrypy
from cherrypy import tools

from asciipic.api import base as base_api
from asciipic.db.managers import user


class EchoEndpoint(base_api.BaseAPI):

    """Action related to users."""

    # A list that contains all the resources (endpoints) available for the
    # current metadata service

    # Whether this application should be available for clients
    exposed = True

    # pylint: disable=no-self-use
    @user.check_credentials
    @tools.json_out()
    def POST(self, **kwargs):
        """Create a new account."""
        cmd = kwargs.pop("data", None)
        response = {
            "meta": {
                "status": True,
                "verbose": "Ok",
                "job_id": 11,
            },
            "content": cmd
        }

        return response

    # pylint: disable=no-self-use
    @user.check_credentials
    @tools.json_out()
    @cherrypy.popargs('job_id')
    def GET(self, job_id=None):
        """Create a new account."""
        response = {
            "meta": {
                "status": True,
                "verbose": "Ok",
                "job_status": "done",
                "job_id": job_id
            },
            "content": None
        }

        return response
