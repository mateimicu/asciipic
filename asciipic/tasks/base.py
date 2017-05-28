"""Base class for Tasks."""
import abc

from oslo_log import log as logging
import six
import rq
from rq import Queue

from asciipic.common import exception
from asciipic import config as asciipic_config
from asciipic.common import tools

CONFIG = asciipic_config.CONFIG

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


def run_task(task):
    """Run the task."""
    redis_con = tools.RedisConnection(
        host=CONFIG.worker.redis_host, port=CONFIG.worker.redis_port,
        db=CONFIG.worker.redis_database,
        password=CONFIG.worker.redis_password)
    queue = Queue(name="high", connection=redis_con.rcon)
    LOG.info("Queue task %s on queue %s", task, queue)
    return queue.enqueue(task)


def get_job_by_id(job_id):
    """Return a job based on the id."""
    redis_con = tools.RedisConnection(
        host=CONFIG.worker.redis_host, port=CONFIG.worker.redis_port,
        db=CONFIG.worker.redis_database,
        password=CONFIG.worker.redis_password)
    LOG.info("Get job with id %s", job_id)
    return rq.job.Job.fetch(job_id, connection=redis_con.rcon)
