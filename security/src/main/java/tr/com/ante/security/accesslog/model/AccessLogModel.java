package tr.com.ante.security.accesslog.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AccessLogModel implements Serializable {

    private Long userId;

    private Date date;

    private String servletPath;

    private String method;

    private Long duration;

    private String request;

    private String response;

    public AccessLogModel(Long userId, Date date, String servletPath, String method, Long duration, String request, String response) {
        this.userId = userId;
        this.date = date;
        this.servletPath = servletPath;
        this.method = method;
        this.duration = duration;
        this.request = request;
        this.response = response;
    }
}