"""AsciiPic base exception handling."""


class AsciipicException(Exception):
    """Base Asciipic exception

    To correctly use this class, inherit from it and define
    a `template` property.

    That `template` will be formated using the keyword arguments
    provided to the constructor.
    """

    template = "An unknown exception occurred."

    def __init__(self, message=None, **kwargs):
        message = message or self.template

        try:
            message = message % kwargs
        except (TypeError, KeyError):
            # Something went wrong during message formatting.
            # Probably kwargs doesn't match a variable in the message.
            message = ("Message: %(template)s. Extra or "
                       "missing info: %(kwargs)s" %
                       {"template": message, "kwargs": kwargs})

        super(AsciipicException, self).__init__(message)


class CliError(AsciipicException):

    """Something went wrong during the processing of command line."""

    template = "Something went wrong during the procesing of command line."


class Invalid(AsciipicException):

    """The received object is not valid."""

    template = "Unacceptable parameters."


class NotFound(AsciipicException):

    """The required object is not available in container."""

    template = "The %(object)r was not found in %(container)s."


class NotSupported(AsciipicException):

    """The functionality required is not available in the current context."""

    template = "%(feature)s is not available in %(context)s."


class InvalidName(AsciipicException):

    """The name was not found in the context."""

    template = "%(name)s not found in the %(list_name) list."
