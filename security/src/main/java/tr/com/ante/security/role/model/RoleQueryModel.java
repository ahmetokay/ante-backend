package tr.com.ante.security.role.model;

import lombok.Data;
import tr.com.ante.core.model.QueryGeneratorModel;
import tr.com.ante.security.privilege.model.PrivilegeModel;

import java.util.List;

@Data
public class RoleQueryModel extends QueryGeneratorModel {

    private String name;

    private List<PrivilegeModel> privilegeList;

}