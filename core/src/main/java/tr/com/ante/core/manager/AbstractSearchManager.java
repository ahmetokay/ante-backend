package tr.com.ante.core.manager;

import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tr.com.ante.core.entity.BaseEntity;
import tr.com.ante.core.exception.ItemNotFoundException;

import java.util.List;

public interface AbstractSearchManager<E extends BaseEntity> {

    E findById(Long id) throws ItemNotFoundException;

    List<E> list();

    Page<E> list(BooleanBuilder builder, Pageable pageable);

    Iterable<E> list(BooleanBuilder builder);

}
