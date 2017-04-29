"""The user Map fror the Image table."""
import sqlalchemy
from sqlalchemy import orm

from asciipic.db.oracle import base


class Image(base.META_BASE):
    """User Map for the Images table."""
    __tablename__ = 'images'
    # pylint:disable=invalid-name, bad-whitespace
    id = sqlalchemy.Column(sqlalchemy.Integer, sqlalchemy.Sequence('id_seq'),
                           primary_key=True, autoincrement=True)
    url = sqlalchemy.Column(sqlalchemy.String(length=2048,
                                              convert_unicode=True),
                            nullable=False)
    post_date = sqlalchemy.Column(sqlalchemy.DateTime, nullable=False)
    crawl_date = sqlalchemy.Column(sqlalchemy.DateTime, nullable=False)
    height = sqlalchemy.Column(sqlalchemy.Integer, nullable=False)
    width = sqlalchemy.Column(sqlalchemy.Integer, nullable=False)
    is_saved = sqlalchemy.Column(sqlalchemy.Boolean, default=False)
    tags = orm.relationship("AssociationTags", back_populates="tags")

    def __repr__(self):
        """Return the representation-ish for this image."""
        repr_str = ("<Image(id='{}', url='{}', post_date='{}', "
                    "crawl_date='{}', height='{}' width='{}', is_saved='{}')")
        return repr_str.format(self.id, self.url,
                               self.post_date, self.crawl_date,
                               self.height, self.width,
                               self.is_saved)
