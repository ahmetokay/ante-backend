package tr.com.ante.elastic.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Data
@Document(indexName = "rest_access_log")
public class AccessLogDocument {

    @Id
    private String uniqueId;

    private Long userId;

    private Date date;

    private String servletPath;

    private String method;

    private Long duration;

    private String request;

    private String response;

}