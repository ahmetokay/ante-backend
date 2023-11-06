package tr.com.ante.security.privilege.converter;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import tr.com.ante.security.privilege.entity.PrivilegeEntity;
import tr.com.ante.security.privilege.model.PrivilegeModel;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-06T20:41:49+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class PrivilegeConverterImpl implements PrivilegeConverter {

    @Override
    public PrivilegeModel toModel(PrivilegeEntity entity) {
        if ( entity == null ) {
            return null;
        }

        PrivilegeModel privilegeModel = new PrivilegeModel();

        privilegeModel.setId( entity.getId() );
        privilegeModel.setActive( entity.isActive() );
        privilegeModel.setUniqueId( entity.getUniqueId() );
        privilegeModel.setCreateDate( entity.getCreateDate() );
        privilegeModel.setCreateUser( entity.getCreateUser() );
        privilegeModel.setUpdateDate( entity.getUpdateDate() );
        privilegeModel.setUpdateUser( entity.getUpdateUser() );
        privilegeModel.setRevisionType( entity.getRevisionType() );
        privilegeModel.setName( entity.getName() );
        privilegeModel.setTitle( entity.getTitle() );
        privilegeModel.setParentId( entity.getParentId() );
        privilegeModel.setChildPrivilegeList( toModelList( entity.getChildPrivilegeList() ) );

        return privilegeModel;
    }

    @Override
    public PrivilegeEntity toEntity(PrivilegeModel dto) {
        if ( dto == null ) {
            return null;
        }

        PrivilegeEntity privilegeEntity = new PrivilegeEntity();

        privilegeEntity.setId( dto.getId() );
        privilegeEntity.setActive( dto.isActive() );
        privilegeEntity.setUniqueId( dto.getUniqueId() );
        privilegeEntity.setCreateDate( dto.getCreateDate() );
        privilegeEntity.setCreateUser( dto.getCreateUser() );
        privilegeEntity.setUpdateDate( dto.getUpdateDate() );
        privilegeEntity.setUpdateUser( dto.getUpdateUser() );
        privilegeEntity.setRevisionType( dto.getRevisionType() );
        privilegeEntity.setName( dto.getName() );
        privilegeEntity.setTitle( dto.getTitle() );
        privilegeEntity.setParentId( dto.getParentId() );
        privilegeEntity.setChildPrivilegeList( toEntityList( dto.getChildPrivilegeList() ) );

        return privilegeEntity;
    }

    @Override
    public List<PrivilegeModel> toModelList(List<PrivilegeEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<PrivilegeModel> list = new ArrayList<PrivilegeModel>( entityList.size() );
        for ( PrivilegeEntity privilegeEntity : entityList ) {
            list.add( toModel( privilegeEntity ) );
        }

        return list;
    }

    @Override
    public List<PrivilegeEntity> toEntityList(List<PrivilegeModel> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<PrivilegeEntity> list = new ArrayList<PrivilegeEntity>( dtoList.size() );
        for ( PrivilegeModel privilegeModel : dtoList ) {
            list.add( toEntity( privilegeModel ) );
        }

        return list;
    }
}
