package tr.com.ante.security.user.model;

import lombok.Data;
import tr.com.ante.core.model.QueryGeneratorModel;

@Data
public class UserQueryModel extends QueryGeneratorModel {

    private String email;
    private String password;
    private String name;
    private String surname;
    private String address;

}