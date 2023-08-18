package tr.com.ante.security.user.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import tr.com.ante.core.model.BaseModel;
import tr.com.ante.security.role.model.RoleModel;
import tr.com.ante.view.Views;

import java.util.List;

@Data
public class UserModel extends BaseModel {

    @JsonView(Views.Private.class)
    private String email;

    private String password;

    @JsonView(Views.Public.class)
    private String name;

    private String surname;

    private String address;

    private List<RoleModel> roleList;

}