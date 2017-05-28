"""User EndPoint for AsciiPic."""
from cherrypy import tools
from oslo_log import log as logging
import validate_email

from asciipic.api import base as base_api
from asciipic.db.managers import user


USERS = user.Users
LOG = logging.getLogger(__name__)


class AccountEndpoint(base_api.BaseAPI):

    """User EndPoint for AsciiPic."""

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
