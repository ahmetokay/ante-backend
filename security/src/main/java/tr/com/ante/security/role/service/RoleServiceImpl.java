package tr.com.ante.security.role.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tr.com.ante.core.converter.BaseConverter;
import tr.com.ante.core.manager.AbstractOperationalManager;
import tr.com.ante.core.manager.AbstractSearchManager;
import tr.com.ante.core.query.AbstractQueryGenerator;
import tr.com.ante.core.service.AbstractGenericServiceImpl;
import tr.com.ante.security.role.converter.RoleConverter;
import tr.com.ante.security.role.entity.RoleEntity;
import tr.com.ante.security.role.manager.RoleOperationalManager;
import tr.com.ante.security.role.manager.RoleSearchManager;
import tr.com.ante.security.role.model.RoleModel;
import tr.com.ante.security.role.model.RoleQueryModel;
import tr.com.ante.security.role.query.RoleQueryGenerator;

@RequiredArgsConstructor
@Component
public class RoleServiceImpl extends AbstractGenericServiceImpl<RoleModel, RoleEntity, RoleQueryModel> implements RoleService {

    private final RoleConverter converter;
    private final RoleSearchManager searchManager;
    private final RoleOperationalManager operationalManager;
    private final RoleQueryGenerator queryGenerator;

    @Override
    protected BaseConverter<RoleModel, RoleEntity> getConverter() {
        return converter;
    }

    @Override
    protected AbstractSearchManager<RoleEntity> getSearchManager() {
        return searchManager;
    }

    @Override
    protected AbstractOperationalManager<RoleEntity> getOperationalManager() {
        return operationalManager;
    }

    @Override
    protected AbstractQueryGenerator getQueryGenerator() {
        return queryGenerator;
    }

}