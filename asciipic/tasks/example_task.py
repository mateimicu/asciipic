"""Base class for Tasks."""

from oslo_log import log as logging

from asciipic.tasks import base
from asciipic.common import exception

LOG = logging.getLogger(__name__)


class ExampleTask(base.BaseTask):
    """Base class for Tasks."""

    def _on_task_done(self, result):
        """What to execute after successfully finished processing a task."""
        LOG.info("We could save this in oracle database is required.")
        LOG.info("Result %s", result)

    def _on_task_fail(self, exc):
        """What to do when the program fails processing a task."""
        LOG.info("We could cleanup some resources or log the error")
        raise exc

    def _prologue(self):
        """Executed once before the taks running."""
        LOG.info("We could create a database connection.")

    def _work(self):
        """Override this with your desired procedures."""
        return "Task Done"

    def _epilogue(self):
        """Executed once after the taks running."""
        LOG.info("We could clean up the database connection.")

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
