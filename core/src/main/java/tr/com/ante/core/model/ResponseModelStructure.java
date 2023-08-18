package tr.com.ante.core.model;

import org.springframework.http.HttpStatus;

public interface ResponseModelStructure<T> {

    ResponseModel ok();

    ResponseModel<T> ok(T data);

    ResponseModel<T> error(T data, HttpStatus status);

}