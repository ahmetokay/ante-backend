package tr.com.ante.security.user.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tr.com.ante.core.manager.AbstractSearchManagerImpl;
import tr.com.ante.core.repository.BaseRepository;
import tr.com.ante.security.user.entity.UserEntity;
import tr.com.ante.security.user.repository.UserRepository;

@RequiredArgsConstructor
@Component
public class UserSearchManagerImpl extends AbstractSearchManagerImpl<UserEntity> implements UserSearchManager {

    private final UserRepository repository;

    @Override
    protected BaseRepository<UserEntity> getDao() {
        return repository;
    }

}