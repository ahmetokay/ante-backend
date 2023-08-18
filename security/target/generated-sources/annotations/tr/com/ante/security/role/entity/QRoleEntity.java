package tr.com.ante.security.role.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRoleEntity is a Querydsl query type for RoleEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRoleEntity extends EntityPathBase<RoleEntity> {

    private static final long serialVersionUID = 2015328551L;

    public static final QRoleEntity roleEntity = new QRoleEntity("roleEntity");

    public final tr.com.ante.core.entity.QBaseEntity _super = new tr.com.ante.core.entity.QBaseEntity(this);

    //inherited
    public final BooleanPath active = _super.active;

    //inherited
    public final DateTimePath<java.util.Date> createDate = _super.createDate;

    //inherited
    public final NumberPath<Long> createUser = _super.createUser;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public final ListPath<tr.com.ante.security.privilege.entity.PrivilegeEntity, tr.com.ante.security.privilege.entity.QPrivilegeEntity> privilegeList = this.<tr.com.ante.security.privilege.entity.PrivilegeEntity, tr.com.ante.security.privilege.entity.QPrivilegeEntity>createList("privilegeList", tr.com.ante.security.privilege.entity.PrivilegeEntity.class, tr.com.ante.security.privilege.entity.QPrivilegeEntity.class, PathInits.DIRECT2);

    //inherited
    public final StringPath uniqueId = _super.uniqueId;

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    //inherited
    public final NumberPath<Long> updateUser = _super.updateUser;

    public QRoleEntity(String variable) {
        super(RoleEntity.class, forVariable(variable));
    }

    public QRoleEntity(Path<? extends RoleEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRoleEntity(PathMetadata metadata) {
        super(RoleEntity.class, metadata);
    }

}

