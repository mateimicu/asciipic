"""User EndPoint for AsciiPic."""

from asciipic.api import base as base_api
from asciipic.api.api_endpoint import version


class UserEndpoint(base_api.BaseAPI):

    """User EndPoint for AsciiPic."""

    # A list that contains all the resources (endpoints) available for the
    # current metadata service
    resources = [
    ]

    # Whether this application should be available for clients
    exposed = True
