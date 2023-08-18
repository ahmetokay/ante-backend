package tr.com.ante.core.exception;

public class CriteriaNotSupportedException extends BaseException {

    public CriteriaNotSupportedException() {
        super();
    }

    public CriteriaNotSupportedException(String message) {
        super(message);
    }

    public CriteriaNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CriteriaNotSupportedException(Throwable cause) {
        super(cause);
    }

    public CriteriaNotSupportedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}