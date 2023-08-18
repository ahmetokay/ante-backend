package tr.com.ante.core.converter;

import tr.com.ante.core.entity.BaseEntity;
import tr.com.ante.core.model.BaseModel;

import java.util.List;

public interface BaseConverter<D extends BaseModel, E extends BaseEntity> {

    D toModel(E entity);

    E toEntity(D dto);

    List<D> toModelList(List<E> entityList);

    List<E> toEntityList(List<D> dtoList);

}