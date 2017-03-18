"""Version info."""
import pbr.version


def get_version():
    """Obtain the project version."""
    version = pbr.version.VersionInfo('asciipic')
    return version.release_string()
