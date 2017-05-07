"""OracleDB Mappings."""
from asciipic.db.oracle import manager
from asciipic.db.oracle import factory

ORACLE_DB = manager.OracleDBManager()

for table in factory.TableFactory.get_items():
    ORACLE_DB.register(table)
