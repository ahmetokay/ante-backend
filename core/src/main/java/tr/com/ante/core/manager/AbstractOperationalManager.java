package tr.com.ante.core.manager;

import tr.com.ante.core.entity.BaseEntity;
import tr.com.ante.core.exception.BaseException;
import tr.com.ante.core.exception.ItemNotFoundException;

public interface AbstractOperationalManager<E extends BaseEntity> {

    E save(E entity) throws BaseException;

    E update(E entity) throws BaseException;

    void deleteByUniqueId(String uniqueId) throws ItemNotFoundException;

}