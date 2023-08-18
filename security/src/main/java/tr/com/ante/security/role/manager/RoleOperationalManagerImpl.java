package tr.com.ante.security.role.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tr.com.ante.core.manager.AbstractOperationalManagerImpl;
import tr.com.ante.core.repository.BaseRepository;
import tr.com.ante.security.role.entity.RoleEntity;
import tr.com.ante.security.role.repository.RoleRepository;

@RequiredArgsConstructor
@Component
public class RoleOperationalManagerImpl extends AbstractOperationalManagerImpl<RoleEntity> implements RoleOperationalManager {

    private final RoleRepository repository;

    @Override
    protected BaseRepository<RoleEntity> getDao() {
        return repository;
    }

}