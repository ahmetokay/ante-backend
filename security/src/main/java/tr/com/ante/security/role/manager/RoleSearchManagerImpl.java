package tr.com.ante.security.role.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tr.com.ante.core.manager.AbstractSearchManagerImpl;
import tr.com.ante.core.repository.BaseRepository;
import tr.com.ante.security.role.entity.RoleEntity;
import tr.com.ante.security.role.repository.RoleRepository;

@RequiredArgsConstructor
@Component
public class RoleSearchManagerImpl extends AbstractSearchManagerImpl<RoleEntity> implements RoleSearchManager {

    private final RoleRepository repository;

    @Override
    protected BaseRepository<RoleEntity> getDao() {
        return repository;
    }

}