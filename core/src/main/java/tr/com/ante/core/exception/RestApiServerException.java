package tr.com.ante.core.exception;

public class RestApiServerException extends BaseException {

    public RestApiServerException() {
        super();
    }

    public RestApiServerException(String message) {
        super(message);
    }

    public RestApiServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestApiServerException(Throwable cause) {
        super(cause);
    }

    public RestApiServerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}