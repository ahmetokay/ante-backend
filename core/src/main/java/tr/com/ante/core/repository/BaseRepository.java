package tr.com.ante.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import tr.com.ante.core.entity.BaseEntity;

import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity> extends JpaRepository<E, Long>, JpaSpecificationExecutor<E>, QuerydslPredicateExecutor<E> {

    <S extends E> S save(S entity);

    Optional<E> findByUniqueId(String uniqueId);

}