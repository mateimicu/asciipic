"""Factory with all the Table Maps."""

from asciipic.common import util


class TableFactory(util.BaseFactory):

    BASE_CLASS = object

    PREFIX = "asciipic.db.oracle"

    NAME = "TableFactory"

    ITEMS = [
        "user",
    ]
