"""Factory for config sections."""

_OPT_PATHS = (
    'asciipic.config.default.AsciipicOptions',
    'asciipic.config.api.AsciipicAPIOptions',
    'asciipic.config.journalize.AsciipicJournalizeOptions',
    'asciipic.config.oracle.AsciipicOracleOptions',
    'asciipic.config.redis.RedisOptions',
    'asciipic.config.worker.WorkerOptions',
)


def _load_class(class_path):
    """Load the module and return the required class."""
    parts = class_path.rsplit('.', 1)
    module = __import__(parts[0], fromlist=parts[1])
    return getattr(module, parts[1])


def get_options():
    """Return a list of all the available `Options` subclasses."""
    return [_load_class(class_path) for class_path in _OPT_PATHS]
