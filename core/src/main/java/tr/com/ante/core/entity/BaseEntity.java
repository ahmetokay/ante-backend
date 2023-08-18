package tr.com.ante.core.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import tr.com.ante.core.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Audited
@Data
@DynamicUpdate
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "is_active")
    private boolean active;

    @Column(name = "unique_id", updatable = false)
    private String uniqueId;

    @CreatedDate
    @Column(name = "create_date", updatable = false)
    private Date createDate;

    @CreatedBy
    @Column(name = "create_user", updatable = false)
    private Long createUser;

    @LastModifiedDate
    @Column(name = "update_date")
    private Date updateDate;

    @LastModifiedBy
    @Column(name = "update_user")
    private Long updateUser;

    @Transient
    private String revisionType;

    @PrePersist
    public void onPrePersist() {
        this.active = true;
        this.uniqueId = UUID.randomUUID().toString();

        audit("INSERT");
    }

    @PreUpdate
    public void onPreUpdate() {

        audit("UPDATE");
    }

    @PreRemove
    public void onPreRemove() {
        audit("DELETE");
    }

    private void audit(String operation) {
        System.out.println(DateUtils.getCurrentDate() + " - " + operation);
//        setOperation(operation);
//        setTimestamp((new Date()).getTime());
    }

}