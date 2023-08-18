package tr.com.ante.test.repository;

import org.springframework.stereotype.Repository;
import tr.com.ante.core.repository.BaseRepository;
import tr.com.ante.test.entity.TestEntity;

@Repository
public interface TestRepository extends BaseRepository<TestEntity> {

}