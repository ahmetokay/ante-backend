package tr.com.ante.core.query;

import com.querydsl.core.BooleanBuilder;
import tr.com.ante.core.entity.BaseEntity;
import tr.com.ante.core.model.QueryGeneratorModel;

import java.lang.reflect.ParameterizedType;

public abstract class AbstractQueryGenerator<E extends BaseEntity, QM extends QueryGeneratorModel> {

    private final Class<E> entityClass;

    public AbstractQueryGenerator() {
        this.entityClass = (Class<E>) (((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]); // TODO entity ait özel standart query ekleme yapmak için gerekecek
    }

    protected abstract void prepareQuery(BooleanBuilder builder, QM queryModel);

    public void generateQuery(BooleanBuilder builder, QM queryModel) {
        prepareQuery(builder, queryModel);
    }
}