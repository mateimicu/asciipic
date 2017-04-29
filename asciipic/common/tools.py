"""Tools used across the project."""
import hashlib
import os

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
