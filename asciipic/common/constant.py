"""Constants used across the project."""

DEFAULT_FORMAT = '%(asctime)s - %(name)s - %(levelname)s - %(message)s'
DEFAULT_LOG_FILE = 'asciipic.log'

TASK_RUNNING = "running"
TASK_DONE = "done"
TASK_FAILED = "failed"

PID_TMP_FILE = "/tmp/asciipic.pid"

ORACLE_HOST = "ORACLE_HOST"
ORACLE_PORT = "ORACLE_PORT"
ORACLE_USERNAME = "ORACLE_USERNAME"
ORACLE_PASSWORD = "ORACLE_PASSWORD"
ORACLE_DBNAME = "ORACLE_DBNAME"

DEFAULT_ORACLE_HOST = "127.0.0.1"
DEFAULT_ORACLE_PORT = "1521"
DEFAULT_ORACLE_USERNAME = "asciipic"
DEFAULT_ORACLE_PASSWORD = "asciipic"
DEFAULT_ORACLE_DBNAME = "xe"

======
ORACLE_DB_URL_FORMAT = ("oracle+cx_oracle://{username}:{password}"
                        "@{host}:{port}/{dbname}")
