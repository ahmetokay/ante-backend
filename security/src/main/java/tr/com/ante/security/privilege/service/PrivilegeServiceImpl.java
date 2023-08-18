package tr.com.ante.security.privilege.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tr.com.ante.core.converter.BaseConverter;
import tr.com.ante.core.manager.AbstractOperationalManager;
import tr.com.ante.core.manager.AbstractSearchManager;
import tr.com.ante.core.query.AbstractQueryGenerator;
import tr.com.ante.core.service.AbstractGenericServiceImpl;
import tr.com.ante.security.privilege.converter.PrivilegeConverter;
import tr.com.ante.security.privilege.entity.PrivilegeEntity;
import tr.com.ante.security.privilege.manager.PrivilegeSearchManager;
import tr.com.ante.security.privilege.model.PrivilegeModel;
import tr.com.ante.security.privilege.model.PrivilegeQueryModel;
import tr.com.ante.security.privilege.query.PrivilegeQueryGenerator;
import tr.com.ante.security.privilege.repository.PrivilegeRepository;

@RequiredArgsConstructor
@Component
public class PrivilegeServiceImpl extends AbstractGenericServiceImpl<PrivilegeModel, PrivilegeEntity, PrivilegeQueryModel> implements PrivilegeService {

    private final PrivilegeRepository repository;
    private final PrivilegeConverter converter;
    private final PrivilegeSearchManager searchManager;
    private final PrivilegeQueryGenerator queryGenerator;

    @Override
    protected BaseConverter<PrivilegeModel, PrivilegeEntity> getConverter() {
        return converter;
    }

    @Override
    protected AbstractSearchManager<PrivilegeEntity> getSearchManager() {
        return searchManager;
    }

    @Override
    protected AbstractOperationalManager<PrivilegeEntity> getOperationalManager() {
        return null;
    }

    @Override
    protected AbstractQueryGenerator getQueryGenerator() {
        return queryGenerator;
    }

//    @Override
//    public PageModel<PrivilegeModel> list() {
//        List<PrivilegeEntity> privilegeEntityList = repository.findByParentIdIsNull();
//
//        PageModel<PrivilegeModel> result = new PageModel<>();
//        result.setData(converter.toModelList(privilegeEntityList));
//        result.setTotalElements(privilegeEntityList.size());
//        return result;
//    }
}