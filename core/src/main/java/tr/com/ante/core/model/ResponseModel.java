package tr.com.ante.core.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@Data
public class ResponseModel<T> implements ResponseModelStructure<T> {

    private T body;
    private List<String> messageList;
    private boolean success;
    private int statusCode;

    public ResponseModel() {
    }

    public ResponseModel(T body) {
        this.body = body;
        this.success = true;
        this.statusCode = HttpStatus.OK.value();
    }

    public ResponseModel(T body, String message, boolean success, HttpStatus status) {
        this.body = body;
        this.messageList = Collections.singletonList(message);
        this.success = success;
        this.statusCode = status.value();
    }

    public ResponseModel(T body, List<String> messageList, boolean success, HttpStatus status) {
        this.body = body;
        this.messageList = messageList;
        this.success = success;
        this.statusCode = status.value();
    }

    @Override
    public ResponseModel ok() {
        this.success = true;
        this.statusCode = HttpStatus.OK.value();

        return this;
    }

    @Override
    public ResponseModel<T> ok(T data) {
        this.body = data;
        this.success = true;
        this.statusCode = HttpStatus.OK.value();

        return this;
    }

    @Override
    public ResponseModel<T> error(T data, HttpStatus status) {
        this.body = data;
        this.success = false;
        this.statusCode = status.value();
        return this;
    }
}