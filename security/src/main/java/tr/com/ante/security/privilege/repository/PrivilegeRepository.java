package tr.com.ante.security.privilege.repository;

import org.springframework.stereotype.Repository;
import tr.com.ante.core.repository.BaseRepository;
import tr.com.ante.security.privilege.entity.PrivilegeEntity;

import java.util.List;

@Repository
public interface PrivilegeRepository extends BaseRepository<PrivilegeEntity> {

    List<PrivilegeEntity> findByParentIdIsNull();

}