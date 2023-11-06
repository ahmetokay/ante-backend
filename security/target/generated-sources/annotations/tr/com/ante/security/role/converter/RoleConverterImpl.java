package tr.com.ante.security.role.converter;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import tr.com.ante.security.privilege.entity.PrivilegeEntity;
import tr.com.ante.security.privilege.model.PrivilegeModel;
import tr.com.ante.security.role.entity.RoleEntity;
import tr.com.ante.security.role.model.RoleModel;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-06T20:41:49+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class RoleConverterImpl implements RoleConverter {

    @Override
    public RoleModel toModel(RoleEntity entity) {
        if ( entity == null ) {
            return null;
        }

        RoleModel roleModel = new RoleModel();

        roleModel.setId( entity.getId() );
        roleModel.setActive( entity.isActive() );
        roleModel.setUniqueId( entity.getUniqueId() );
        roleModel.setCreateDate( entity.getCreateDate() );
        roleModel.setCreateUser( entity.getCreateUser() );
        roleModel.setUpdateDate( entity.getUpdateDate() );
        roleModel.setUpdateUser( entity.getUpdateUser() );
        roleModel.setRevisionType( entity.getRevisionType() );
        roleModel.setName( entity.getName() );
        roleModel.setPrivilegeList( privilegeEntityListToPrivilegeModelList( entity.getPrivilegeList() ) );

        return roleModel;
    }

    @Override
    public RoleEntity toEntity(RoleModel dto) {
        if ( dto == null ) {
            return null;
        }

        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setId( dto.getId() );
        roleEntity.setActive( dto.isActive() );
        roleEntity.setUniqueId( dto.getUniqueId() );
        roleEntity.setCreateDate( dto.getCreateDate() );
        roleEntity.setCreateUser( dto.getCreateUser() );
        roleEntity.setUpdateDate( dto.getUpdateDate() );
        roleEntity.setUpdateUser( dto.getUpdateUser() );
        roleEntity.setRevisionType( dto.getRevisionType() );
        roleEntity.setName( dto.getName() );
        roleEntity.setPrivilegeList( privilegeModelListToPrivilegeEntityList( dto.getPrivilegeList() ) );

        return roleEntity;
    }

    @Override
    public List<RoleModel> toModelList(List<RoleEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<RoleModel> list = new ArrayList<RoleModel>( entityList.size() );
        for ( RoleEntity roleEntity : entityList ) {
            list.add( toModel( roleEntity ) );
        }

        return list;
    }

    @Override
    public List<RoleEntity> toEntityList(List<RoleModel> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<RoleEntity> list = new ArrayList<RoleEntity>( dtoList.size() );
        for ( RoleModel roleModel : dtoList ) {
            list.add( toEntity( roleModel ) );
        }

        return list;
    }

    protected List<PrivilegeModel> privilegeEntityListToPrivilegeModelList(List<PrivilegeEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<PrivilegeModel> list1 = new ArrayList<PrivilegeModel>( list.size() );
        for ( PrivilegeEntity privilegeEntity : list ) {
            list1.add( privilegeEntityToPrivilegeModel( privilegeEntity ) );
        }

        return list1;
    }

    protected PrivilegeModel privilegeEntityToPrivilegeModel(PrivilegeEntity privilegeEntity) {
        if ( privilegeEntity == null ) {
            return null;
        }

        PrivilegeModel privilegeModel = new PrivilegeModel();

        privilegeModel.setId( privilegeEntity.getId() );
        privilegeModel.setActive( privilegeEntity.isActive() );
        privilegeModel.setUniqueId( privilegeEntity.getUniqueId() );
        privilegeModel.setCreateDate( privilegeEntity.getCreateDate() );
        privilegeModel.setCreateUser( privilegeEntity.getCreateUser() );
        privilegeModel.setUpdateDate( privilegeEntity.getUpdateDate() );
        privilegeModel.setUpdateUser( privilegeEntity.getUpdateUser() );
        privilegeModel.setRevisionType( privilegeEntity.getRevisionType() );
        privilegeModel.setName( privilegeEntity.getName() );
        privilegeModel.setTitle( privilegeEntity.getTitle() );
        privilegeModel.setParentId( privilegeEntity.getParentId() );
        privilegeModel.setChildPrivilegeList( privilegeEntityListToPrivilegeModelList( privilegeEntity.getChildPrivilegeList() ) );

        return privilegeModel;
    }

    protected List<PrivilegeEntity> privilegeModelListToPrivilegeEntityList(List<PrivilegeModel> list) {
        if ( list == null ) {
            return null;
        }

        List<PrivilegeEntity> list1 = new ArrayList<PrivilegeEntity>( list.size() );
        for ( PrivilegeModel privilegeModel : list ) {
            list1.add( privilegeModelToPrivilegeEntity( privilegeModel ) );
        }

        return list1;
    }

    protected PrivilegeEntity privilegeModelToPrivilegeEntity(PrivilegeModel privilegeModel) {
        if ( privilegeModel == null ) {
            return null;
        }

        PrivilegeEntity privilegeEntity = new PrivilegeEntity();

        privilegeEntity.setId( privilegeModel.getId() );
        privilegeEntity.setActive( privilegeModel.isActive() );
        privilegeEntity.setUniqueId( privilegeModel.getUniqueId() );
        privilegeEntity.setCreateDate( privilegeModel.getCreateDate() );
        privilegeEntity.setCreateUser( privilegeModel.getCreateUser() );
        privilegeEntity.setUpdateDate( privilegeModel.getUpdateDate() );
        privilegeEntity.setUpdateUser( privilegeModel.getUpdateUser() );
        privilegeEntity.setRevisionType( privilegeModel.getRevisionType() );
        privilegeEntity.setName( privilegeModel.getName() );
        privilegeEntity.setTitle( privilegeModel.getTitle() );
        privilegeEntity.setParentId( privilegeModel.getParentId() );
        privilegeEntity.setChildPrivilegeList( privilegeModelListToPrivilegeEntityList( privilegeModel.getChildPrivilegeList() ) );

        return privilegeEntity;
    }
}
