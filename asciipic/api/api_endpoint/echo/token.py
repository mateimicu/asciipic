"""User EndPoint for AsciiPic."""
import cherrypy
from cherrypy import tools
from oslo_log import log as logging

from asciipic.api import base as base_api
from asciipic.db.managers import user
from asciipic.common import exception


USERS = user.Users
LOG = logging.getLogger(__name__)


class TokenEndpoint(base_api.BaseAPI):

    """User EndPoint for AsciiPic."""

    # Whether this application should be available for clients
    exposed = True

    # pylint: disable=no-self-use
    @tools.json_out()
    def POST(self, **kwargs):
        """Return a list with the users or a token."""
        username = kwargs.pop("username", None)
        password = kwargs.pop("password", None)

        response = {
            "meta": {
                "status": True,
                "verbose": "Ok"
            },
            "content": None
        }
        if not any([username, password]):
            # We want a list
            response["meta"]["verbose"] = "All usernames."
            response["content"] = USERS.get_all_usernames()

            return response
        token = None
        try:
            token = USERS.get_token(username, password)
            response["content"] = {"token": token}
        except exception.InvalidCredentials:
            response["meta"]["status"] = False
            response["meta"]["verbose"] = (
                "Invalid credentails for user {}").format(username)
        except exception.TooManyItems as ex:
            LOG.error("[GET] Error while processing %s: %s", username, ex)
            raise cherrypy.HTTPError(
                500, "Error while processing your request.")
        except exception.ItemNotFound:
            # NOTE(mmicu): Not so sure about this, having a
            # wrong password or a wrong username could
            # result in the same error
            response["meta"]["status"] = False
            response["meta"]["verbose"] = (
                "No username {} found.").format(username)
        except exception.UnableToGenerateToken:
            response["meta"]["status"] = False
            response["meta"]["verbose"] = (
                "Unable to generate token for {}").format(username)

        return response
