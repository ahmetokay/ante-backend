package tr.com.ante.security.user.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tr.com.ante.core.manager.AbstractOperationalManagerImpl;
import tr.com.ante.core.repository.BaseRepository;
import tr.com.ante.security.user.entity.UserEntity;
import tr.com.ante.security.user.repository.UserRepository;

@RequiredArgsConstructor
@Component
public class UserOperationalManagerImpl extends AbstractOperationalManagerImpl<UserEntity> implements UserOperationalManager {

    private final UserRepository repository;

    @Override
    protected BaseRepository<UserEntity> getDao() {
        return repository;
    }

}