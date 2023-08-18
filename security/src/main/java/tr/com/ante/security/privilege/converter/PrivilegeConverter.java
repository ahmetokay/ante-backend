package tr.com.ante.security.privilege.converter;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import tr.com.ante.core.converter.BaseConverter;
import tr.com.ante.security.privilege.entity.PrivilegeEntity;
import tr.com.ante.security.privilege.model.PrivilegeModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PrivilegeConverter extends BaseConverter<PrivilegeModel, PrivilegeEntity> {

    @Override
    @Named("toModel")
    PrivilegeModel toModel(PrivilegeEntity entity);

    @Override
    @Named("toEntity")
    PrivilegeEntity toEntity(PrivilegeModel dto);

    @Override
    @IterableMapping(qualifiedByName = "toModel")
    List<PrivilegeModel> toModelList(List<PrivilegeEntity> entityList);

    @Override
    @IterableMapping(qualifiedByName = "toEntity")
    List<PrivilegeEntity> toEntityList(List<PrivilegeModel> dtoList);

}