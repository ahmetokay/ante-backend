package tr.com.ante.test.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tr.com.ante.core.manager.AbstractOperationalManagerImpl;
import tr.com.ante.core.repository.BaseRepository;
import tr.com.ante.test.entity.TestEntity;
import tr.com.ante.test.repository.TestRepository;

@RequiredArgsConstructor
@Component
public class TestOperationalManagerImpl extends AbstractOperationalManagerImpl<TestEntity> implements TestOperationalManager {

    private final TestRepository repository;

    @Override
    protected BaseRepository<TestEntity> getDao() {
        return repository;
    }

}