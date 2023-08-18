package tr.com.ante.security.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tr.com.ante.core.exception.BaseException;
import tr.com.ante.core.exception.ItemNotFoundException;
import tr.com.ante.core.exception.ResourceForbiddenExpiredException;
import tr.com.ante.core.exception.SessionExpiredException;
import tr.com.ante.core.model.ResponseModel;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class SecurityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {SessionExpiredException.class})
    protected ResponseEntity<Object> sessionExpiredExceptionHandler(SessionExpiredException ex, WebRequest request) {
        return new ResponseEntity<>(new ResponseModel<>(null, ex.getLocalizedMessage(), false, HttpStatus.UNAUTHORIZED), HttpStatus.OK);
    }

    @ExceptionHandler(value = {ItemNotFoundException.class})
    protected ResponseEntity<Object> itemNotFoundExceptionHandler(ItemNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(new ResponseModel<>(null, ex.getLocalizedMessage(), false, HttpStatus.NOT_FOUND), HttpStatus.OK);
    }

    @ExceptionHandler(value = {BaseException.class})
    protected ResponseEntity<Object> exceptionHandler(BaseException ex, WebRequest request) {
        return new ResponseEntity<>(new ResponseModel<>(null, ex.getLocalizedMessage(), false, HttpStatus.OK), HttpStatus.OK);
    }

    @ExceptionHandler(value = {ResourceForbiddenExpiredException.class})
    protected ResponseEntity<Object> resourceForbiddenExceptionHandler(ResourceForbiddenExpiredException ex, WebRequest request) {
        return new ResponseEntity<>(new ResponseModel<>(null, ex.getLocalizedMessage(), false, HttpStatus.FORBIDDEN), HttpStatus.OK);
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    protected ResponseEntity<Object> accessDeniedExceptionHandler(AccessDeniedException ex, WebRequest request) {
        return new ResponseEntity<>(new ResponseModel<>(null, ex.getLocalizedMessage(), false, HttpStatus.FORBIDDEN), HttpStatus.OK);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> exceptionHandler(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new ResponseModel<>(null, ex.getLocalizedMessage(), false, HttpStatus.OK), HttpStatus.OK);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<Object> runtimeExceptionHandler(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(new ResponseModel<>(null, ex.getLocalizedMessage(), false, HttpStatus.OK), HttpStatus.OK);
    }
}