package tr.com.ante.security.user.converter;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import tr.com.ante.core.converter.BaseConverter;
import tr.com.ante.security.user.entity.UserEntity;
import tr.com.ante.security.user.model.UserModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserConverter extends BaseConverter<UserModel, UserEntity> {

    @Override
    @Mapping(target = "password", ignore = true)
    @Named("toModel")
    UserModel toModel(UserEntity entity);

    @Override
    @Named("toEntity")
    UserEntity toEntity(UserModel dto);

    @Override
    @IterableMapping(qualifiedByName = "toModel")
    List<UserModel> toModelList(List<UserEntity> entityList);

    @Override
    @IterableMapping(qualifiedByName = "toEntity")
    List<UserEntity> toEntityList(List<UserModel> dtoList);

}