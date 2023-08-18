package tr.com.ante.security.user.service;

import tr.com.ante.core.service.AbstractGenericService;
import tr.com.ante.security.user.entity.UserEntity;
import tr.com.ante.security.user.model.UserModel;
import tr.com.ante.security.user.model.UserQueryModel;

public interface UserService extends AbstractGenericService<UserModel, UserEntity, UserQueryModel> {

    void resetPassword(UserModel userModel);

}