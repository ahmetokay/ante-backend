package tr.com.ante.security.user.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tr.com.ante.core.repository.BaseRepository;
import tr.com.ante.security.user.entity.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<UserEntity> {

    Optional<UserEntity> findByEmail(String email);

    @Query("select name from UserEntity where email =:email and active = true")
    String getPasswordFromEmail(@Param("email") String email);

}