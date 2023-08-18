package tr.com.ante.core.model;

import lombok.Data;

@Data
public class CriteriaModel {

    private String fieldName;
    private Object value;
    private CriteriaOperationType operation;
    private String joinEntity;
    private String type;

}