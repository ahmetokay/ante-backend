package tr.com.ante.security.user.converter;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import tr.com.ante.security.privilege.entity.PrivilegeEntity;
import tr.com.ante.security.privilege.model.PrivilegeModel;
import tr.com.ante.security.role.entity.RoleEntity;
import tr.com.ante.security.role.model.RoleModel;
import tr.com.ante.security.user.entity.UserEntity;
import tr.com.ante.security.user.model.UserModel;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-02T11:50:50+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class UserConverterImpl implements UserConverter {

    @Override
    public UserModel toModel(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UserModel userModel = new UserModel();

        userModel.setId( entity.getId() );
        userModel.setActive( entity.isActive() );
        userModel.setUniqueId( entity.getUniqueId() );
        userModel.setCreateDate( entity.getCreateDate() );
        userModel.setCreateUser( entity.getCreateUser() );
        userModel.setUpdateDate( entity.getUpdateDate() );
        userModel.setUpdateUser( entity.getUpdateUser() );
        userModel.setRevisionType( entity.getRevisionType() );
        userModel.setEmail( entity.getEmail() );
        userModel.setName( entity.getName() );
        userModel.setSurname( entity.getSurname() );
        userModel.setAddress( entity.getAddress() );
        userModel.setRoleList( roleEntityListToRoleModelList( entity.getRoleList() ) );

        return userModel;
    }

    @Override
    public UserEntity toEntity(UserModel dto) {
        if ( dto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( dto.getId() );
        userEntity.setActive( dto.isActive() );
        userEntity.setUniqueId( dto.getUniqueId() );
        userEntity.setCreateDate( dto.getCreateDate() );
        userEntity.setCreateUser( dto.getCreateUser() );
        userEntity.setUpdateDate( dto.getUpdateDate() );
        userEntity.setUpdateUser( dto.getUpdateUser() );
        userEntity.setRevisionType( dto.getRevisionType() );
        userEntity.setEmail( dto.getEmail() );
        userEntity.setPassword( dto.getPassword() );
        userEntity.setName( dto.getName() );
        userEntity.setSurname( dto.getSurname() );
        userEntity.setAddress( dto.getAddress() );
        userEntity.setRoleList( roleModelListToRoleEntityList( dto.getRoleList() ) );

        return userEntity;
    }

    @Override
    public List<UserModel> toModelList(List<UserEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<UserModel> list = new ArrayList<UserModel>( entityList.size() );
        for ( UserEntity userEntity : entityList ) {
            list.add( toModel( userEntity ) );
        }

        return list;
    }

    @Override
    public List<UserEntity> toEntityList(List<UserModel> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<UserEntity> list = new ArrayList<UserEntity>( dtoList.size() );
        for ( UserModel userModel : dtoList ) {
            list.add( toEntity( userModel ) );
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

    protected RoleModel roleEntityToRoleModel(RoleEntity roleEntity) {
        if ( roleEntity == null ) {
            return null;
        }

        RoleModel roleModel = new RoleModel();

        roleModel.setId( roleEntity.getId() );
        roleModel.setActive( roleEntity.isActive() );
        roleModel.setUniqueId( roleEntity.getUniqueId() );
        roleModel.setCreateDate( roleEntity.getCreateDate() );
        roleModel.setCreateUser( roleEntity.getCreateUser() );
        roleModel.setUpdateDate( roleEntity.getUpdateDate() );
        roleModel.setUpdateUser( roleEntity.getUpdateUser() );
        roleModel.setRevisionType( roleEntity.getRevisionType() );
        roleModel.setName( roleEntity.getName() );
        roleModel.setPrivilegeList( privilegeEntityListToPrivilegeModelList( roleEntity.getPrivilegeList() ) );

        return roleModel;
    }

    protected List<RoleModel> roleEntityListToRoleModelList(List<RoleEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<RoleModel> list1 = new ArrayList<RoleModel>( list.size() );
        for ( RoleEntity roleEntity : list ) {
            list1.add( roleEntityToRoleModel( roleEntity ) );
        }

        return list1;
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

    protected RoleEntity roleModelToRoleEntity(RoleModel roleModel) {
        if ( roleModel == null ) {
            return null;
        }

        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setId( roleModel.getId() );
        roleEntity.setActive( roleModel.isActive() );
        roleEntity.setUniqueId( roleModel.getUniqueId() );
        roleEntity.setCreateDate( roleModel.getCreateDate() );
        roleEntity.setCreateUser( roleModel.getCreateUser() );
        roleEntity.setUpdateDate( roleModel.getUpdateDate() );
        roleEntity.setUpdateUser( roleModel.getUpdateUser() );
        roleEntity.setRevisionType( roleModel.getRevisionType() );
        roleEntity.setName( roleModel.getName() );
        roleEntity.setPrivilegeList( privilegeModelListToPrivilegeEntityList( roleModel.getPrivilegeList() ) );

        return roleEntity;
    }

    protected List<RoleEntity> roleModelListToRoleEntityList(List<RoleModel> list) {
        if ( list == null ) {
            return null;
        }

        List<RoleEntity> list1 = new ArrayList<RoleEntity>( list.size() );
        for ( RoleModel roleModel : list ) {
            list1.add( roleModelToRoleEntity( roleModel ) );
        }

        return list1;
    }
}
