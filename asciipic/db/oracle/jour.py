"""The user Map fror the Journalize table."""
import sqlalchemy
from sqlalchemy_utils.types import choice

from asciipic.db.oracle import base
from asciipic.common import constant


class Journalize(base.META_BASE):
    """User Map for the Journalize table."""
    __tablename__ = 'jour'
    # pylint:disable=invalid-name, bad-whitespace
    id = sqlalchemy.Column(sqlalchemy.Integer, sqlalchemy.Sequence('id_seq'),
                           primary_key=True, autoincrement=True)
    action_date = sqlalchemy.Column(sqlalchemy.DateTime, nullable=False)
    action = sqlalchemy.Column(choice.ChoiceType(constant.ACTION_TYPES))

    def __repr__(self):
        """Return the representation-ish for this image."""
        repr_str = ("<Journalize(id='{}', action_date='{}', action='{}')")
        return repr_str.format(self.id, self.action_date, self.action)
