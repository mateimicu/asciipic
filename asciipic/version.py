"""Version info."""
import pbr.version


def get_version():
    """Obtain the project version."""
    try:
        version = pbr.version.VersionInfo('asciipic')
        return version.release_string()
    except Exception:
        return "0.0.0"
        
