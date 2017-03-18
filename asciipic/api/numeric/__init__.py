"""Numeric endpoint for the AsciiPic API."""

from asciipic.api import base as base_api
from asciipic.api.numeric import resource


class NumericEndpoint(base_api.BaseAPI):

    """Numeric endpoint for the AsciiPic API."""

    # A list that contains all the resources (endpoints) available for the
    # current metadata service
    resources = [
        ("add", resource.AddNumberds),
        ("diff", resource.SubtractNumbers),
    ]

    # Whether this application should be available for clients
    exposed = True
