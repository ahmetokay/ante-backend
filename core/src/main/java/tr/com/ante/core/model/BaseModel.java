package tr.com.ante.core.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public abstract class BaseModel implements Serializable {

    private Long id;

    private boolean active;

    private String uniqueId;

    private Date createDate;

    private Long createUser;

    private Date updateDate;

    private Long updateUser;

    private String revisionType;

}