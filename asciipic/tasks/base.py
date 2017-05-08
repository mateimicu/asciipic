"""Base class for Tasks."""
import abc

from oslo_log import log as logging
import six

from asciipic.common import exception

LOG = logging.getLogger(__name__)


@six.add_metaclass(abc.ABCMeta)
class BaseTask(object):
    """Base class for Tasks."""

    @abc.abstractmethod
    def _on_task_done(self, result):
        """What to execute after successfully finished processing a task."""
        pass

    @abc.abstractmethod
    def _on_task_fail(self, exc):
        """What to do when the program fails processing a task."""
        pass

    def _prologue(self):
        """Executed once before the taks running."""
        pass

    def _work(self):
        """Override this with your desired procedures."""
        pass

    def _epilogue(self):
        """Executed once after the taks running."""
        pass

    def __call__(self):
        """Run the task."""
        result = None

        try:
            self._prologue()
            result = self._work()
            self._epilogue()
        except exception.AsciipicException as exc:
            self._on_task_fail(exc)
        else:
            self._on_task_done(result)

        return result
