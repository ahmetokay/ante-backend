package tr.com.ante.test.service;

import tr.com.ante.core.service.AbstractGenericService;
import tr.com.ante.test.entity.TestEntity;
import tr.com.ante.test.model.TestModel;
import tr.com.ante.test.model.TestQueryModel;

public interface TestService extends AbstractGenericService<TestModel, TestEntity, TestQueryModel> {

}