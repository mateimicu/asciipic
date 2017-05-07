"""Worker using Python-RQ project."""

import rq

from oslo_log import log as logging

from asciipic import config as asciipic_config
from asciipic.common import exception
from asciipic.common import tools
from asciipic.worker import base

CONFIG = asciipic_config.CONFIG
LOG = logging.getLogger(__name__)


class Worker(base.BaseWorker):
    """Base class for a worker."""

    def __init__(self, queues=CONFIG.worker.queues,
                 name=CONFIG.worker.name, **kwargs):
        """Initialize the worker.

        :param queues: Queues to listen
        :param name: Worker name
        :param redis_host: The redis ip/hostname
        :param redis_port: The redis port
        :param redis_database: Redis database to use for the channels
        :param redis_password: Password for redis connection
        """
        super(Worker, self).__init__(queues=queues, name=name)
        self._redis_host = kwargs.get("redis_host", CONFIG.worker.redis_host)
        self._redis_port = kwargs.get("redis_port", CONFIG.worker.redis_port)
        self._redis_database = kwargs.get("redis_database",
                                          CONFIG.worker.redis_database)
        self._redis_password = kwargs.get("redis_password",
                                          CONFIG.worker.redis_password)
        self._redis_con = None
        self._worker = None

    @property
    def rcon(self):
        """Return the redis connection."""
        if not self._redis_con:
            try:
                self._redis_con = tools.RedisConnection(
                    host=self._redis_host, port=self._redis_port,
                    db=self._redis_database, password=self._redis_password)
            except exception.AsciipicException as ex:
                LOG.warn("Failed to connect to redis %s", ex)

        return self._redis_con.rcon

    @property
    def worker(self):
        """Return the worker class."""
        if not self._worker:
            self._worker = rq.Worker(queues=self._queues, name=self._name)
        return self._worker

    def work(self):
        """Start the worker."""

        with rq.Connection(self.rcon):
            LOG.info("Starting worker %s on %s redis: %s:%s/%s",
                     self._name, self._queues, self._redis_host,
                     self._redis_port, self._redis_database)
            self.worker.work()
