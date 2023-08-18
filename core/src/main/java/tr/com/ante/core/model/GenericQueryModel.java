package tr.com.ante.core.model;

import lombok.Data;

import java.util.List;

@Data
public class GenericQueryModel<T extends QueryGeneratorModel> extends QueryModel {

    private List<CriteriaModel> filters;

    private T query;

}