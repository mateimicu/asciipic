"""The user Map fror the Tag table."""
import sqlalchemy
from sqlalchemy import orm

from asciipic.db.oracle import base


class AssociationTags(base.META_BASE):
    """User Map for the Image-Tag Association table."""
    __tablename__ = 'image_tags_association'
    # pylint:disable=invalid-name
    id = sqlalchemy.Column(sqlalchemy.Integer, sqlalchemy.Sequence('id_seq'),
                           primary_key=True, autoincrement=True)
    image_id = sqlalchemy.Column(sqlalchemy.Integer,
                                 sqlalchemy.ForeignKey('tags.id'),
                                 primary_key=True)
    tag_id = sqlalchemy.Column(sqlalchemy.Integer,
                               sqlalchemy.ForeignKey('images.id'),
                               primary_key=True)
    image = orm.relationship("Image", back_populates="images")
    tag = orm.relationship("Tag", back_populates="tags")

    def __repr__(self):
        """Return the representation-ish for this image."""
        repr_str = ("<Tag(id='{}', name='{}')")
        return repr_str.format(self.id, self.name)
