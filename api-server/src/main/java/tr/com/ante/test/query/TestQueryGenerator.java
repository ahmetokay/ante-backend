package tr.com.ante.test.query;

import com.querydsl.core.BooleanBuilder;
import org.springframework.stereotype.Component;
import tr.com.ante.core.query.AbstractQueryGenerator;
import tr.com.ante.test.entity.TestEntity;
import tr.com.ante.test.model.TestQueryModel;

@Component
public class TestQueryGenerator extends AbstractQueryGenerator<TestEntity, TestQueryModel> {

    @Override
    protected void prepareQuery(BooleanBuilder builder, TestQueryModel queryModel) {

    }
}