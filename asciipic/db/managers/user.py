"""Users DB manager for AsciiPic."""
import sqlalchemy
from oslo_log import log as logging

from asciipic.db import oracle
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
            users = session.query(USER).filter(USER.username.like(username)).count()
        except sqlalchemy.exc.DatabaseError as ex:
            LOG.error("DB query for %s and got : %s", username, ex)
            raise exception.QueryError(msg=ex)

        return users == 0

    @staticmethod
    def check_credentials(username, password):
        """Check if credentials of one user are """
        session = ORACLE_DB.session
        try:
            data = session.query(USER).filter(USER.username.like(username)).all()
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

            return [user.pop() for user in data if len(user) > 0]
        except sqlalchemy.exc.IntegrityError as ex:
            LOG.error("DB query for %s and got : %s", "all usernames", ex)
            raise exception.QueryError(msg=ex)


