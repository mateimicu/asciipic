"""Tools used across the project."""
import hashlib
import os

import redis

from asciipic.common import constant


def hash_password(password):
    """Return the hash of the password and the salt.:

    :param password: The password you want to hash
    :return: (hash, salt)
    """
    salt = os.urandom(8)
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
<<<<<<< HEAD
=======

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
            raise exception.ArestorException(
                "Failed to connect to Redis Server.")

        return True

    @property
    def rcon(self):
        """Return a Redis connection."""
        self.refresh()
        return self._rcon
