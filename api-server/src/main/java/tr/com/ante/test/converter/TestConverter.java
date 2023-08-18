package tr.com.ante.test.converter;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import tr.com.ante.core.converter.BaseConverter;
import tr.com.ante.test.entity.TestEntity;
import tr.com.ante.test.model.TestModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TestConverter extends BaseConverter<TestModel, TestEntity> {

    @Override
    @Named("toModel")
    TestModel toModel(TestEntity entity);

    @Override
    @Named("toEntity")
    TestEntity toEntity(TestModel dto);

    @Override
    @IterableMapping(qualifiedByName = "toModel")
    List<TestModel> toModelList(List<TestEntity> entityList);

    @Override
    @IterableMapping(qualifiedByName = "toEntity")
    List<TestEntity> toEntityList(List<TestModel> dtoList);

}