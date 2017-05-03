"""Users DB manager for AsciiPic."""
import datetime

import sqlalchemy
from oslo_log import log as logging

from asciipic.db import oracle
from asciipic.common import constant
from asciipic.common import exception
from asciipic.common import tools

ORACLE_DB = oracle.ORACLE_DB
LOG = logging.getLogger(__name__)
# NOTE(mmicu): Pylint nu poate vedea membri
# setati dinamic
# pylint: disable=no-member
USER = ORACLE_DB.User


class Users(object):
    """Users manager."""

    @staticmethod
    def check_email(email):
        """Check if the credentials are valid for a new account."""
        try:
            session = ORACLE_DB.session
            users = session.query(USER).filter(USER.email.like(email)).count()
        except sqlalchemy.exc.DatabaseError as ex:
            LOG.error("DB query for %s and got : %s", email, ex)
            raise exception.QueryError(msg=ex)

        return users == 0

    @staticmethod
    def check_username(username):
        """Check if the credentials are valid for a new account."""
        try:
            session = ORACLE_DB.session
            users = session.query(USER).filter(
                USER.username.like(username)).count()
        except sqlalchemy.exc.DatabaseError as ex:
            LOG.error("DB query for %s and got : %s", username, ex)
            raise exception.QueryError(msg=ex)

        return users == 0

    @staticmethod
    def check_credentials(username, password):
        """Check if credentials of one user are """
        session = ORACLE_DB.session
        try:
            data = session.query(USER).filter(
                USER.username.like(username)).all()
            if len(data) < 1:
                raise exception.ItemNotFound(id=username)
            elif len(data) > 1:
                raise exception.TooManyItems(expected=1, found=len(data))

            user = data.pop()

            return tools.check_password(password, user.password, user.salt)
        except sqlalchemy.exc.IntegrityError as ex:
            LOG.error("DB query for %s and got : %s", username, ex)
            raise exception.QueryError(msg=ex)

    @staticmethod
    def create_user(username, password, email):
        # TODO(mmicu): Jurnalieaza acest eveniment
        """Create a new user."""
        session = ORACLE_DB.session
        enc_password, salt = tools.hash_password(password)
        try:
            # NOTE(mmicu): Momentan la crearea unui nou user
            # acestaeste activ si confirmat, in viitor
            # vom avea confirmare prin email
            user = USER(username=username, password=enc_password,
                        salt=salt, email=email, is_confirmed=True,
                        is_active=True)
            session.add(user)
            session.commit()
        except sqlalchemy.exc.DatabaseError as ex:
            LOG.error("DB query for %s and got : %s", username, ex)
            raise exception.QueryError(msg=ex)

    @staticmethod
    def get_all_usernames():
        """Get a list with all usernames"""
        session = ORACLE_DB.session
        try:
            data = session.query(USER.username).all()
            return [user.username for user in data]
        except sqlalchemy.exc.IntegrityError as ex:
            LOG.error("DB query for %s and got : %s", "all usernames", ex)
            raise exception.QueryError(msg=ex)

    @classmethod
    def get_token(cls, username, password):
        """Get a new token for the user.

        :param username: The user name
        :param password: The password forthe username
        """
        session = ORACLE_DB.session
        redis_con = tools.RedisConnection()
        try:
            valid_user = cls.check_credentials(username, password)

            if not valid_user:
                LOG.error("Ivalid credentials for user %s", username)
                raise exception.InvalidCredentials(username=username)

            users = session.query(USER).filter(
                USER.username.like(username)).all()

            if len(users) > 1:
                raise exception.TooManyItems(expected=1, found=len(users))

            # Query toate informatiile despre user
            user = users.pop()

            # Verificam daca avem deja un token pentru
            # un user cu id-ul asta
            id_template = constant.ID_FORMAT.format(userid=user.id)
            old_token = redis_con.rcon.get(id_template)

            # Daca avem deja un token pentru acest user
            # trebuie sa il stergem
            if old_token:
                old_token_template = constant.TOKEN_FORMAT.format(
                    token=old_token)
                redis_con.rcon.delete(old_token_template)
                redis_con.rcon.delete(id_template)

            # Generam un token si key pentru redis
            token = tools.generate_token(userid=user.id)
            token_template = constant.TOKEN_FORMAT.format(token=token)

            # Generam ttl-urile pentru token si id
            ttl_token = datetime.timedelta(minutes=constant.TTL_TOKEN_MINUTES)
            ttl_id = ttl_token - datetime.timedelta(
                seconds=constant.TTL_ID_OFFSET_SECONDS)

            # Setam valorile in redis cu ttl-ul corespunzator
            redis_con.rcon.setex(token_template, ttl_token, user.id)
            redis_con.rcon.setex(id_template, ttl_id, token)

            return token
        except exception.QueryError as ex:
            LOG.error("DB query for %s and got : %s", username, ex)
            raise exception.UnableToGenerateToken(username=username)


    @classmethod
    def check_token(cls, token):
        """Check the token, if it's valid return the USER.

        :param token: The user token
        """
        session = ORACLE_DB.session
        redis_con = tools.RedisConnection()

        userid = redis_con.get(token)
        if not userid:
            # The token expired
            return userid
        try:
            user = session.query(USER).filter(USER.id.like(userid)).first()
            return user
        except exception.QueryError as ex:
            LOG.error("DB query for id: %s with token and got : %s",
                      userid, token, ex)
            raise exception.QueryError(msg=ex)
