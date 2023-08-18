package tr.com.ante.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QErasableEntity is a Querydsl query type for ErasableEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QErasableEntity extends EntityPathBase<ErasableEntity> {

    private static final long serialVersionUID = 115177963L;

    public static final QErasableEntity erasableEntity = new QErasableEntity("erasableEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final BooleanPath active = _super.active;

    //inherited
    public final DateTimePath<java.util.Date> createDate = _super.createDate;

    //inherited
    public final NumberPath<Long> createUser = _super.createUser;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath uniqueId = _super.uniqueId;

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    //inherited
    public final NumberPath<Long> updateUser = _super.updateUser;

    public QErasableEntity(String variable) {
        super(ErasableEntity.class, forVariable(variable));
    }

    public QErasableEntity(Path<? extends ErasableEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QErasableEntity(PathMetadata metadata) {
        super(ErasableEntity.class, metadata);
    }

}

