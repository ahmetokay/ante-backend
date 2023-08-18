package tr.com.ante.security.role.repository;

import org.springframework.stereotype.Repository;
import tr.com.ante.core.repository.BaseRepository;
import tr.com.ante.security.role.entity.RoleEntity;

@Repository
public interface RoleRepository extends BaseRepository<RoleEntity> {
}