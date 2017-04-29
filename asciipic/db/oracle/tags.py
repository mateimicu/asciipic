"""The user Map fror the Tag table."""
import sqlalchemy
from sqlalchemy import orm

from asciipic.db.oracle import base


class Tag(base.META_BASE):
    """User Map for the Tag table."""
    __tablename__ = 'tags'
    # pylint:disable=invalid-name, bad-whitespace
    id = sqlalchemy.Column(sqlalchemy.Integer, sqlalchemy.Sequence('id_seq'),
                           primary_key=True, autoincrement=True)
    name = sqlalchemy.Column(sqlalchemy.String(length=60,
                                               convert_unicode=True))

    # images = orm.relationship("AssociationTags", back_populates="image")
    images = orm.relationship("AssociationTags")
    def __repr__(self):
        """Return the representation-ish for this image."""
        repr_str = ("<Tag(id='{}', name='{}')")
        return repr_str.format(self.id, self.name)
