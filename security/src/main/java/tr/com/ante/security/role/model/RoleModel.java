package tr.com.ante.security.role.model;

import lombok.Data;
import tr.com.ante.core.model.BaseModel;
import tr.com.ante.security.privilege.model.PrivilegeModel;

import java.util.List;

@Data
public class RoleModel extends BaseModel {

    private String name;

    private List<PrivilegeModel> privilegeList;

}