"""Constants used across the project."""

DEFAULT_FORMAT = '%(asctime)s - %(name)s - %(levelname)s - %(message)s'
DEFAULT_LOG_FILE = 'asciipic.log'

TASK_RUNNING = "running"
TASK_DONE = "done"
TASK_FAILED = "failed"

PID_TMP_FILE = "/tmp/asciipic.pid"
PID_WORKER_TMP_FILE = "/tmp/asciipic-worker.pid"

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

ORACLE_DB_URL_FORMAT = ("oracle+cx_oracle://{username}:{password}"
                        "@{host}:{port}/{dbname}")

ACTION_TYPES = [
    ('login', 'login'),
    ('logout', 'logout'),
]

PASSWORD_HASH_TEMPLATE = "{alg}${password}${salt}"
HASHING_ALG = "sha256"
SALT_LEN = 8

TOKEN_HASHING_TEMPLATE = "{userid}${time}${salt}"
TOKEN_FORMAT = "TOKEN_{token}"
ID_FORMAT = "ID_{userid}"

USER_ID = "U_id"
USER_USERNAME = "U_username"

# NOTE(mmicu): astea ar putea fi si config options
TTL_TOKEN_MINUTES = 30
TTL_ID_OFFSET_SECONDS = 10

# NOTE(mmicu): astea ar putea fi si config options
RETRY_COUNT = 20
RETRY_DELAY = 5

LOG_DOMAIN = "asciipic"

REDIS_URL_ENV = "REDIS_URL"
