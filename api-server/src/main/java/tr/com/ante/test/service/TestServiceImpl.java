package tr.com.ante.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tr.com.ante.core.converter.BaseConverter;
import tr.com.ante.core.manager.AbstractOperationalManager;
import tr.com.ante.core.manager.AbstractSearchManager;
import tr.com.ante.core.query.AbstractQueryGenerator;
import tr.com.ante.core.service.AbstractGenericServiceImpl;
import tr.com.ante.test.converter.TestConverter;
import tr.com.ante.test.entity.TestEntity;
import tr.com.ante.test.manager.TestOperationalManager;
import tr.com.ante.test.manager.TestSearchManager;
import tr.com.ante.test.model.TestModel;
import tr.com.ante.test.model.TestQueryModel;
import tr.com.ante.test.query.TestQueryGenerator;

@RequiredArgsConstructor
@Component
public class TestServiceImpl extends AbstractGenericServiceImpl<TestModel, TestEntity, TestQueryModel> implements TestService {

    private final TestConverter converter;
    private final TestSearchManager searchManager;
    private final TestOperationalManager operationalManager;
    private final TestQueryGenerator queryGenerator;

    @Override
    protected BaseConverter<TestModel, TestEntity> getConverter() {
        return converter;
    }

    @Override
    protected AbstractSearchManager<TestEntity> getSearchManager() {
        return searchManager;
    }

    @Override
    protected AbstractOperationalManager<TestEntity> getOperationalManager() {
        return operationalManager;
    }

    @Override
    protected AbstractQueryGenerator<TestEntity, TestQueryModel> getQueryGenerator() {
        return queryGenerator;
    }

}