"""Tools used across the project."""
import hashlib
import random
import time
import string
import functools


from oslo_log import log as logging

import redis

from asciipic.common import constant
from asciipic.common import exception
from asciipic import config as asciipic_config


CONFIG = asciipic_config.CONFIG
LOG = logging.getLogger(__name__)

CHARS = string.ascii_uppercase + string.digits


def get_salt(length):
    """Get a cryptographically secure salt."""
    return ''.join(random.SystemRandom().choice(CHARS) for _ in range(length))


def hash_password(password):
    """Return the hash of the password and the salt.:

    :param password: The password you want to hash
    :return: (hash, salt)
    """
    salt = get_salt(constant.SALT_LEN)
    password_template = constant.PASSWORD_HASH_TEMPLATE.format(
        alg=constant.HASHING_ALG,
        password=password,
        salt=salt)
    hashed_password = hashlib.sha256(password_template).hexdigest()
    return (hashed_password, salt)


def check_password(raw_password, enc_password, salt):
    """Check if the raw password matches the hashed one.:

    :param raw_password: The raw password
    :param enc_password: The encripted/hashed password
    :param salt: The salt used in hasing

    :rtype: True if the password matches
    """
    password_template = constant.PASSWORD_HASH_TEMPLATE.format(
        alg=constant.HASHING_ALG,
        password=raw_password,
        salt=salt)
    hashed_password = hashlib.sha256(password_template).hexdigest()
    return enc_password == hashed_password


def generate_token(userid):
    """Generate a random token for the user_id"""
    salt = get_salt(128)
    token_template = constant.TOKEN_HASHING_TEMPLATE.format(
        userid=userid, time=time.time(), salt=salt)

    sha512 = hashlib.sha512()
    sha512.update(token_template)
    return sha512.hexdigest()


class RedisConnection(object):

    """High level wrapper over the redis data structures operations."""

    def __init__(self):
        """Instantiates objects able to store and retrieve data."""
        self._rcon = None
        self._host = CONFIG.redis.host
        self._port = CONFIG.redis.port
        self._db = CONFIG.redis.database
        self.refresh()

    def _connect(self):
        """Try establishing a connection until succeeds."""
        try:
            rcon = redis.StrictRedis(self._host, self._port, self._db)
            # Return the connection only if is valid and reachable
            if not rcon.ping():
                return None
        except (redis.ConnectionError, redis.RedisError) as exc:
            LOG.error("Failed to connect to Redis Server: %s", exc)
            return None

        return rcon

    def refresh(self, tries=3):
        """Re-establish the connection only if is dropped."""
        for _ in range(tries):
            try:
                if not self._rcon or not self._rcon.ping():
                    self._rcon = self._connect()
                else:
                    break
            except redis.ConnectionError as exc:
                LOG.error("Failed to connect to Redis Server: %s", exc)
        else:
            raise exception.AsciipicException(
                "Failed to connect to Redis Server.")

        return True

    @property
    def rcon(self):
        """Return a Redis connection."""
        self.refresh()
        return self._rcon


# pylint: disable=invalid-name
class retry(object):
    """Decorator that allow the method to be fail."""
    def __init__(self, retry_count=constant.RETRY_COUNT,
                 retry_delay=constant.RETRY_DELAY,
                 exceptions=(Exception, )):
        """Initializa the parameters.

        :param retry_count: Now many times to try
        :param retry_delay: Delay between tries
        :param exception: The exception that sould be catched
                          can be one exception or a tuple of exceptions
        """
        self._retry_count = retry_count
        self._retry_delay = retry_delay
        if isinstance(exceptions, tuple):
            self._exceptions = exceptions
        else:
            self._exceptions = (exceptions, )

    def __call__(self, method):
        """Wrap the method.

        :param method: The method we want ot wrap.
        """
        @functools.wraps(method)
        def wrapper(*args, **kwargs):
            result = None
            for index in range(self._retry_count):
                try:
                    result = method(*args, **kwargs)
                    break
                # pylint: disable=catching-non-exception
                except self._exceptions as ex:
                    LOG.warn("Fail - %s in <%s.%s> with %s",
                             index, method.__module__,
                             method.__name__, ex)
                    time.sleep(self._retry_delay)
            else:
                # Too many tries
                raise exception.FailedTooManyTimes(method=method.__name__,
                                                   module=method.__module__)
            return result
        return wrapper
