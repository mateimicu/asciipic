"""Base class for the Worker."""
import abc

from oslo_log import log as logging
import six

from asciipic import config as asciipic_config

CONFIG = asciipic_config.CONFIG
LOG = logging.getLogger(__name__)


@six.add_metaclass(abc.ABCMeta)
class BaseWorker(object):
    """Base class for a worker."""

    # pylint: disable=unused-argument
    def __init__(self, queues=CONFIG.worker.queues,
                 name=CONFIG.worker.name):
        """Initialize the worker.

        :param queues: Queues to listen
        :param name: Worker name
        """
        self._queues = queues
        self._name = name

    @abc.abstractmethod
    def work(self):
        """Start the worker."""
        pass
