package tr.com.ante.security.role.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;
import tr.com.ante.core.entity.BaseEntity;
import tr.com.ante.security.privilege.entity.PrivilegeEntity;

import java.util.ArrayList;
import java.util.List;

@Audited
@Data
@Where(clause = "is_active = true")
@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_privilege",
            joinColumns = @JoinColumn(name = "fk_role_id", referencedColumnName = "id", nullable = false, updatable = false, insertable = false),
            inverseJoinColumns = @JoinColumn(name = "fk_privilege_id", referencedColumnName = "id", nullable = false, updatable = false, insertable = false))
    private List<PrivilegeEntity> privilegeList = new ArrayList<>();

}