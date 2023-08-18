package tr.com.ante.security.privilege.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Where;
import org.hibernate.annotations.WhereJoinTable;
import org.hibernate.envers.Audited;
import tr.com.ante.core.entity.BaseEntity;

import java.util.List;

@Audited
@Data
@Where(clause = "is_active = true")
@Entity
@Table(name = "privilege")
public class PrivilegeEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "parent_id")
    private Long parentId;

    @OneToMany(fetch = FetchType.EAGER)
    @WhereJoinTable(clause = "parent_id is not null")
    @JoinTable(name = "privilege",
            joinColumns = @JoinColumn(name = "parent_id"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private List<PrivilegeEntity> childPrivilegeList;

    @Override
    public String toString() {
        return "PrivilegeEntity{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}