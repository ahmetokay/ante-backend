package tr.com.ante.handler;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(2)
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /*
    @ExceptionHandler(value = {UserNotFoundException.class})
    protected ResponseEntity<Object> userNotFoundHandler(RuntimeException ex, WebRequest request) {
        ResponseModel<Object> responseModel = new ResponseModel<>(null, ex.getLocalizedMessage(), Boolean.FALSE);
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @ExceptionHandler(value = {SessionExpiredException.class})
    protected ResponseEntity<Object> sessionExpiredHandler(RuntimeException ex, WebRequest request) {
        ResponseModel<Object> responseModel = new ResponseModel<>(null, ex.getLocalizedMessage(), Boolean.FALSE);
        return new ResponseEntity<>(responseModel, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {BaseException.class})
    protected ResponseEntity<Object> exceptionHandler(RuntimeException ex, WebRequest request) {
        ResponseModel<Object> responseModel = new ResponseModel<>(null, ex.getLocalizedMessage(), Boolean.FALSE);
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

     */
}