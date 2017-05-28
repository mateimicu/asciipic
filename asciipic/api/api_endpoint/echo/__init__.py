"""API  endpoint for the AsciiPic API."""
import cherrypy
from cherrypy import tools

from asciipic.api import base as base_api
from asciipic.db.managers import user
from asciipic.tasks import base as base_task
from asciipic.tasks import echo_task


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
        """Post a new echo job."""
        data = kwargs.pop("data", "")
        sleep = bool(data.lstrip("echo").strip())
        task = echo_task.Echo(sleep=sleep)
        job = base_task.run_task(task)
        response = {
            "meta": {
                "status": True,
                "verbose": "Ok",
                "job_id": job.id,
            },
            "content": sleep
        }

        return response

    # pylint: disable=no-self-use, arguments-differ
    @user.check_credentials
    @tools.json_out()
    @cherrypy.popargs('job_id')
    def GET(self, job_id=None):
        """Get the result of the echo job."""
        job = base_task.get_job_by_id(job_id)
        response = {
            "meta": {
                "status": True,
                "verbose": "Ok",
                "job_status": job.status,
                "job_id": job.id
            },
            "content": job.result
        }

        return response
