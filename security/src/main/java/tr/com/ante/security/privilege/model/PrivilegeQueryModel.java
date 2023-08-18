package tr.com.ante.security.privilege.model;

import lombok.Data;
import tr.com.ante.core.model.QueryGeneratorModel;

@Data
public class PrivilegeQueryModel extends QueryGeneratorModel {

    private String name;
    private String title;
    private Long parentId;

}