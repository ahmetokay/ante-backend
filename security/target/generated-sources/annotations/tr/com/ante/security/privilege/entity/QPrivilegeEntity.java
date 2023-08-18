package tr.com.ante.security.privilege.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPrivilegeEntity is a Querydsl query type for PrivilegeEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPrivilegeEntity extends EntityPathBase<PrivilegeEntity> {

    private static final long serialVersionUID = -853346943L;

    public static final QPrivilegeEntity privilegeEntity = new QPrivilegeEntity("privilegeEntity");

    public final tr.com.ante.core.entity.QBaseEntity _super = new tr.com.ante.core.entity.QBaseEntity(this);

    //inherited
    public final BooleanPath active = _super.active;

    public final ListPath<PrivilegeEntity, QPrivilegeEntity> childPrivilegeList = this.<PrivilegeEntity, QPrivilegeEntity>createList("childPrivilegeList", PrivilegeEntity.class, QPrivilegeEntity.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.util.Date> createDate = _super.createDate;

    //inherited
    public final NumberPath<Long> createUser = _super.createUser;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public final NumberPath<Long> parentId = createNumber("parentId", Long.class);

    public final StringPath title = createString("title");

    //inherited
    public final StringPath uniqueId = _super.uniqueId;

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    //inherited
    public final NumberPath<Long> updateUser = _super.updateUser;

    public QPrivilegeEntity(String variable) {
        super(PrivilegeEntity.class, forVariable(variable));
    }

    public QPrivilegeEntity(Path<? extends PrivilegeEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPrivilegeEntity(PathMetadata metadata) {
        super(PrivilegeEntity.class, metadata);
    }

}

