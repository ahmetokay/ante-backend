package tr.com.ante.core.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class PageModel<S extends Serializable> {

    private List<S> data = new ArrayList<>();

    private long totalElements;

}