package tr.com.ante.security.accesslog.model;

import lombok.Data;
import tr.com.ante.core.model.QueryGeneratorModel;

import java.util.Date;

@Data
public class AccessLogQueryModel extends QueryGeneratorModel {

    private Long userId;

    private Date date;

    private String servletPath;

    private String method;

    private String request;

    private String response;

}