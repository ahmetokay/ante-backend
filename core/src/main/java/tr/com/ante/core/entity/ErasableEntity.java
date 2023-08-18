package tr.com.ante.core.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class ErasableEntity extends BaseEntity {

}