"""API  endpoint for the AsciiPic API."""

from asciipic.api import base as base_api
from asciipic.api.api_endpoint.user import account
from asciipic.api.api_endpoint.user import token


class UserEndpoint(base_api.BaseAPI):

    """Action related to users."""

    # A list that contains all the resources (endpoints) available for the
    # current metadata service
    resources = [
        ("account", account.AccountEndpoint),
        ("token", token.TokenEndpoint),
    ]

    # Whether this application should be available for clients
    exposed = True
