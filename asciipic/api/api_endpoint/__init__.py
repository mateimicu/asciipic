"""API  endpoint for the AsciiPic API."""

from asciipic.api import base as base_api
from asciipic.api.api_endpoint import version
from asciipic.api.api_endpoint import auth_version
from asciipic.api.api_endpoint import user
from asciipic.api.api_endpoint import echo


class APIEndpoint(base_api.BaseAPI):

    """This API will have all the API endpoints."""

    # A list that contains all the resources (endpoints) available for the
    # current metadata service
    resources = [
        ("auth_version", auth_version.Version),
        ("version", version.Version),
        ("user", user.UserEndpoint),
        ("echo", echo.EchoEndpoint),
    ]

    # Whether this application should be available for clients
    exposed = True
