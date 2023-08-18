package tr.com.ante.core.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tr.com.ante.core.entity.BaseEntity;
import tr.com.ante.core.entity.ErasableEntity;
import tr.com.ante.core.exception.BaseException;
import tr.com.ante.core.exception.ItemNotFoundException;
import tr.com.ante.core.repository.BaseRepository;

import java.text.MessageFormat;
import java.util.Optional;

@Slf4j
@Component
public abstract class AbstractOperationalManagerImpl<E extends BaseEntity> implements AbstractOperationalManager<E> {

    protected abstract BaseRepository<E> getDao();

    @Override
    public E save(E entity) throws BaseException {
        return getDao().save(entity);
    }

    @Override
    public E update(E entity) throws BaseException {
        getDao().findById(entity.getId()).orElseThrow(() -> new ItemNotFoundException(MessageFormat.format("Sistemde {0} idli kayit bulunamadı.", entity.getId())));

        return getDao().save(entity);
    }

    @Override
    public void deleteByUniqueId(String uniqueId) throws ItemNotFoundException {
        Optional<E> optional = getDao().findByUniqueId(uniqueId);
        if (optional.isPresent()) {
            E entity = optional.get();
            if (entity instanceof ErasableEntity) {
                getDao().delete(entity);
            } else {
                entity.setActive(false);
                getDao().save(entity);
            }
        } else {
            throw new ItemNotFoundException(MessageFormat.format("Sistemde {0} numaralı kayıt bulunamamıştır.", uniqueId));
        }
    }
}