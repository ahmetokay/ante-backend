package tr.com.ante.security.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserEntity is a Querydsl query type for UserEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserEntity extends EntityPathBase<UserEntity> {

    private static final long serialVersionUID = 1623552209L;

    public static final QUserEntity userEntity = new QUserEntity("userEntity");

    public final tr.com.ante.core.entity.QBaseEntity _super = new tr.com.ante.core.entity.QBaseEntity(this);

    //inherited
    public final BooleanPath active = _super.active;

    public final StringPath address = createString("address");

    //inherited
    public final DateTimePath<java.util.Date> createDate = _super.createDate;

    //inherited
    public final NumberPath<Long> createUser = _super.createUser;

    public final StringPath email = createString("email");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final ListPath<tr.com.ante.security.role.entity.RoleEntity, tr.com.ante.security.role.entity.QRoleEntity> roleList = this.<tr.com.ante.security.role.entity.RoleEntity, tr.com.ante.security.role.entity.QRoleEntity>createList("roleList", tr.com.ante.security.role.entity.RoleEntity.class, tr.com.ante.security.role.entity.QRoleEntity.class, PathInits.DIRECT2);

    public final StringPath surname = createString("surname");

    //inherited
    public final StringPath uniqueId = _super.uniqueId;

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    //inherited
    public final NumberPath<Long> updateUser = _super.updateUser;

    public QUserEntity(String variable) {
        super(UserEntity.class, forVariable(variable));
    }

    public QUserEntity(Path<? extends UserEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserEntity(PathMetadata metadata) {
        super(UserEntity.class, metadata);
    }

}

