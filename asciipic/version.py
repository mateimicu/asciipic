"""Version info."""
import pbr.version
HARD_VERSION = "0.0.1"


def get_version():
    """Obtain the project version."""
    try:
        version = pbr.version.VersionInfo('asciipic')
        return version.release_string()
    # pylint:disable=broad-except
    except Exception:
        return HARD_VERSION
