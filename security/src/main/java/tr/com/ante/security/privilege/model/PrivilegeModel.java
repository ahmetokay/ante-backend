package tr.com.ante.security.privilege.model;

import lombok.Data;
import tr.com.ante.core.model.BaseModel;

import java.util.List;

@Data
public class PrivilegeModel extends BaseModel {

    private String name;
    private String title;
    private Long parentId;
    private List<PrivilegeModel> childPrivilegeList;

}