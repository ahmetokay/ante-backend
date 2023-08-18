package tr.com.ante.security.user.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;
import tr.com.ante.core.entity.BaseEntity;
import tr.com.ante.security.role.entity.RoleEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Audited
@Data
@Where(clause = "is_active = true")
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "address")
    private String address;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "fk_user_id", referencedColumnName = "id", nullable = false, updatable = false, insertable = false),
            inverseJoinColumns = @JoinColumn(name = "fk_role_id", referencedColumnName = "id", nullable = false, updatable = false, insertable = false))
    private List<RoleEntity> roleList = new ArrayList<>();

}