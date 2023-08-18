package tr.com.ante.security.privilege.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tr.com.ante.core.manager.AbstractSearchManagerImpl;
import tr.com.ante.core.repository.BaseRepository;
import tr.com.ante.security.privilege.entity.PrivilegeEntity;
import tr.com.ante.security.privilege.repository.PrivilegeRepository;

@RequiredArgsConstructor
@Component
public class PrivilegeSearchManagerImpl extends AbstractSearchManagerImpl<PrivilegeEntity> implements PrivilegeSearchManager {

    private final PrivilegeRepository repository;

    @Override
    protected BaseRepository<PrivilegeEntity> getDao() {
        return repository;
    }

}