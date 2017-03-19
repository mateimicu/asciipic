"""This module contains the entry point for the command line application."""

import argparse
import sys


from asciipic.cli import base as cli_base
from asciipic.cli import commands as cli_commands


class AsciipicCli(cli_base.Application):

    """Command line application for interacting with InstaCli."""

    commands = [
        (cli_commands.Server, "commands"),
    ]

    def setup(self):
        """Setup the command line parser.

        Extend the parser configuration in order to expose all
        the received commands.
        """
        self._parser = argparse.ArgumentParser()
        commands = self._parser.add_subparsers(title="[commands]",
                                               dest="command")

        self._register_parser("commands", commands)


def main():
    """The Cars Scrap command line application."""
    asciipic = AsciipicCli(sys.argv[1:])
    asciipic.run()
    return asciipic.result


if __name__ == "__main__":
    main()
