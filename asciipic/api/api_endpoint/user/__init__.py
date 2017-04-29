"""User EndPoint for AsciiPic."""
import cherrypy
from cherrypy import tools
from oslo_log import log as logging
import validate_email

from asciipic.api import base as base_api
from asciipic.db.managers import user
from asciipic.common import exception


USERS = user.Users
LOG = logging.getLogger(__name__)


class UserEndpoint(base_api.BaseAPI):

    """User EndPoint for AsciiPic."""

    # A list that contains all the resources (endpoints) available for the
    # current metadata service
    resources = [
    ]

    # Whether this application should be available for clients
    exposed = True

    # pylint: disable=no-self-use
    @tools.json_out()
    def POST(self, **kwargs):
        """Create a new account."""
        response = {
            "meta": {
                "status": True,
                "verbose": "Ok"
            },
            "content": None
        }
        username = kwargs.pop("username", None)
        password = kwargs.pop("password", None)
        email = kwargs.pop("email", None)

        # Password check
        if len(password) not in range(5, 31):
            response["meta"]["status"] = False
            response["meta"]["verbose"] = ("Password not the"
                                           " proper length [5, 30].")

        # Email checks
        if not USERS.check_email(email):
            response["meta"]["status"] = False
            response["meta"]["verbose"] = "Email already used."

        if not validate_email.validate_email(email):
            response["meta"]["status"] = False
            response["meta"]["verbose"] = "Email-ul nu este valid"

        # Username checks
        if not USERS.check_username(username):
            response["meta"]["status"] = False
            response["meta"]["verbose"] = "Username already used."

        if len(username) not in range(3, 21):
            response["meta"]["status"] = False
            response["meta"]["verbose"] = ("Username not the"
                                           " proper length [3, 20].")

        if response["meta"]["status"]:
            response["meta"]["verbose"] = "User {} created".format(
                username)
            USERS.create_user(username, password, email)

        return response

    @tools.json_out()
    def GET(self, **kwargs):
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
