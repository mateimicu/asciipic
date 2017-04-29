"""The user Map fror the Users table."""
import sqlalchemy

from asciipic.db.oracle import manager
from asciipic.db.oracle import base

class User(base.META_BASE):
    """User Map for the Users table."""
    __tablename__ = 'users'
    # pylint:disable=invalid-name, bad-whitespace
    id = sqlalchemy.Column(sqlalchemy.Integer,
                           sqlalchemy.Sequence('id_seq'),
                           primary_key=True,
                           autoincrement=True)
    username = sqlalchemy.Column(sqlalchemy.String(length=32,
                                                   convert_unicode=True),
                                 nullable=False)
    email = sqlalchemy.Column(sqlalchemy.String(length=64,
                                                convert_unicode=True),
                              nullable=False)
    password = sqlalchemy.Column(sqlalchemy.String(length=256,
                                                   convert_unicode=True),
                                 nullable=False)
    salt = sqlalchemy.Column(sqlalchemy.String(length=8, convert_unicode=True),
                             nullable=False)
    is_confirmed = sqlalchemy.Column(sqlalchemy.Boolean)
    is_active = sqlalchemy.Column(sqlalchemy.Boolean)

    def __repr__(self):
        """Return the representation-ish for this user."""
        repr_str = ("<User(id='{}', username='{}', email='{}',"
                    " is_confirmed='{}', is_active='{}')")
        return repr_str.format(self.id, self.username,
                               self.email, self.is_confirmed,
                               self.is_active)

    @staticmethod
    def valid_info(username, email):
        """Check if the credentials are valid for a new account."""
    # session

