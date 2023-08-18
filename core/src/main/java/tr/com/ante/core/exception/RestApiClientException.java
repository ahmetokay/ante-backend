package tr.com.ante.core.exception;

public class RestApiClientException extends BaseException {

    public RestApiClientException() {
        super();
    }

    public RestApiClientException(String message) {
        super(message);
    }

    public RestApiClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestApiClientException(Throwable cause) {
        super(cause);
    }

    public RestApiClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}