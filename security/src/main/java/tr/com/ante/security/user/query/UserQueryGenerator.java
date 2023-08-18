package tr.com.ante.security.user.query;

import com.querydsl.core.BooleanBuilder;
import org.springframework.stereotype.Component;
import tr.com.ante.core.query.AbstractQueryGenerator;
import tr.com.ante.security.user.entity.QUserEntity;
import tr.com.ante.security.user.entity.UserEntity;
import tr.com.ante.security.user.model.UserQueryModel;

@Component
public class UserQueryGenerator extends AbstractQueryGenerator<UserEntity, UserQueryModel> {

    @Override
    protected void prepareQuery(BooleanBuilder builder, UserQueryModel queryModel) {

        if (queryModel.getName() != null) {
            builder.and(QUserEntity.userEntity.name.like(queryModel.getName()));
        }

        if (queryModel.getEmail() != null) {
            builder.and(QUserEntity.userEntity.email.like("%" + queryModel.getEmail() + "%"));
        }
    }
}