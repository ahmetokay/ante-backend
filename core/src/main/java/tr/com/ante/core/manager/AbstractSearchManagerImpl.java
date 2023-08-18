package tr.com.ante.core.manager;

import com.querydsl.core.BooleanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import tr.com.ante.core.entity.BaseEntity;
import tr.com.ante.core.exception.ItemNotFoundException;
import tr.com.ante.core.repository.BaseRepository;

import java.text.MessageFormat;
import java.util.List;

@Slf4j
@Component
public abstract class AbstractSearchManagerImpl<E extends BaseEntity> implements AbstractSearchManager<E> {

    protected abstract BaseRepository<E> getDao();

    @Override
    public E findById(Long id) throws ItemNotFoundException {
        return getDao().findById(id).orElseThrow(() -> new ItemNotFoundException(MessageFormat.format("Sistemde {0} numaralı kayıt bulunamamıştır.", id)));
    }

    @Override
    public List list() {
        return getDao().findAll();
    }

    @Override
    public Page<E> list(BooleanBuilder builder, Pageable pageable) {
        return getDao().findAll(builder, pageable);
    }

    @Override
    public Iterable<E> list(BooleanBuilder builder) {
        return getDao().findAll(builder);
    }
}