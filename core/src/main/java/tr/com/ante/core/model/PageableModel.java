package tr.com.ante.core.model;

import lombok.Data;
import tr.com.ante.core.constants.Constants;
import tr.com.ante.core.enm.SortOrder;

import java.io.Serializable;

@Data
public class PageableModel implements Serializable {

    private int page = 0;

    private int size = Constants.DEFAULT_PAGE_SIZE;

    private String sortField = "id";

    private SortOrder sortOrder = SortOrder.ASC;

}