package tr.com.ante.core.exception;

public class ResourceForbiddenExpiredException extends BaseException {
    public ResourceForbiddenExpiredException() {
        super();
    }

    public ResourceForbiddenExpiredException(String message) {
        super(message);
    }

    public ResourceForbiddenExpiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceForbiddenExpiredException(Throwable cause) {
        super(cause);
    }

    public ResourceForbiddenExpiredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}