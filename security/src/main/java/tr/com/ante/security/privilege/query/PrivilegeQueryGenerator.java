package tr.com.ante.security.privilege.query;

import com.querydsl.core.BooleanBuilder;
import org.springframework.stereotype.Component;
import tr.com.ante.core.query.AbstractQueryGenerator;
import tr.com.ante.security.privilege.entity.PrivilegeEntity;
import tr.com.ante.security.privilege.model.PrivilegeQueryModel;

@Component
public class PrivilegeQueryGenerator extends AbstractQueryGenerator<PrivilegeEntity, PrivilegeQueryModel> {

    @Override
    protected void prepareQuery(BooleanBuilder builder, PrivilegeQueryModel queryModel) {

    }
}