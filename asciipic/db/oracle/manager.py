"""OracleBD connection base."""
import sqlalchemy
from sqlalchemy.ext import declarative
from sqlalchemy.orm import sessionmaker
from oslo_log import log as logging

from asciipic import config as asciipic_config
from asciipic.common import constant
from asciipic.common import tools
from asciipic.common import exception

CONFIG = asciipic_config.CONFIG
LOG = logging.getLogger(__name__)


class OracleDBManager(object):
    def __init__(self):
        self._url = constant.ORACLE_DB_URL_FORMAT.format(
            username=CONFIG.oracle.username,
            password=CONFIG.oracle.password,
            host=CONFIG.oracle.host,
            port=CONFIG.oracle.port,
            dbname=CONFIG.oracle.dbname)
        LOG.info(
            "Open OracleDB engine with %s",
            self._url.replace(CONFIG.oracle.password,
                              "*" * len(CONFIG.oracle.password)))
        self._engine = None
        self._base = None
        self._tables = {}
        self._session_const = None

    @property
    def engine(self):
        """DB connection engine."""
        if not self._engine:
            self._engine = sqlalchemy.create_engine(
                self._url, convert_unicode=True, echo=True)
        return self._engine

    @property
    def base(self):
        """Base for the DB mapping."""
        if not self._base:
            self._base = declarative.declarative_base()
        return self._base

    def register_base(self, base):
        """Register the base."""
        self._base = base

    @property
    def conn(self):
        return self.engine.connect()

    @tools.retry(exceptions=sqlalchemy.exc.DatabaseError)
    def create_all(self, base):
        """Create all """
        if self._base != base:
            self._base = base
        self._base.metadata.create_all(self.engine)

    @property
    def session(self):
        if not self._session_const:
            self._session_const = sessionmaker(bind=self.engine)
        return self._session_const()

    def register(self, table):
        """Register a new Table to the manager."""
        name = table.__name__
        if hasattr(self, name):
            raise exception.TableNameAlreadyExists(name=name)
        self._tables[name] = table
        setattr(self, name, table)
