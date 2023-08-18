package tr.com.ante.security.role.converter;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import tr.com.ante.core.converter.BaseConverter;
import tr.com.ante.security.role.entity.RoleEntity;
import tr.com.ante.security.role.model.RoleModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleConverter extends BaseConverter<RoleModel, RoleEntity> {

    @Override
    @Named("toModel")
    RoleModel toModel(RoleEntity entity);

    @Override
    @Named("toEntity")
    RoleEntity toEntity(RoleModel dto);

    @Override
    @IterableMapping(qualifiedByName = "toModel")
    List<RoleModel> toModelList(List<RoleEntity> entityList);

    @Override
    @IterableMapping(qualifiedByName = "toEntity")
    List<RoleEntity> toEntityList(List<RoleModel> dtoList);
}