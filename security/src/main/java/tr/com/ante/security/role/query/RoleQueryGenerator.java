package tr.com.ante.security.role.query;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import tr.com.ante.core.query.AbstractQueryGenerator;
import tr.com.ante.security.privilege.converter.PrivilegeConverter;
import tr.com.ante.security.role.entity.QRoleEntity;
import tr.com.ante.security.role.entity.RoleEntity;
import tr.com.ante.security.role.model.RoleQueryModel;

@RequiredArgsConstructor
@Component
public class RoleQueryGenerator extends AbstractQueryGenerator<RoleEntity, RoleQueryModel> {

    private final PrivilegeConverter privilegeConverter;

    @Override
    protected void prepareQuery(BooleanBuilder builder, RoleQueryModel queryModel) {

        if (!CollectionUtils.isEmpty(queryModel.getPrivilegeList())) {
            builder.and(QRoleEntity.roleEntity.privilegeList.contains(privilegeConverter.toEntity(queryModel.getPrivilegeList().get(0))));
        }
    }
}