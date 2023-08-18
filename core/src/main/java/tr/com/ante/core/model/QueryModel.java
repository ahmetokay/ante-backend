package tr.com.ante.core.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class QueryModel implements Serializable {

    private PageableModel pageable;

}