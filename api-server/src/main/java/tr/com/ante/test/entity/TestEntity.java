package tr.com.ante.test.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;
import tr.com.ante.core.entity.BaseEntity;

@Audited
@Data
@Where(clause = "is_active = true")
@Entity
@Table(name = "test")
public class TestEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

}