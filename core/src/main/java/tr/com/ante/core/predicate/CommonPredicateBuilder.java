package tr.com.ante.core.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.util.CollectionUtils;
import tr.com.ante.core.model.CriteriaModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The Class CommonPredicateBuilder.
 *
 * @param <T> the generic type
 */
public class CommonPredicateBuilder<T> {

    private final Class<T> t;

    private final String entityVariable;

    private List<CriteriaModel> criteriaList;

    public CommonPredicateBuilder(Class<T> t) {
        this.t = t;
        this.entityVariable = getEntityVariable(t.getSimpleName());
        this.criteriaList = new ArrayList<>();
    }

    private static String getEntityVariable(String simpleClassName) {
        char[] c = simpleClassName.toCharArray();
        c[0] = Character.toLowerCase(c[0]);
        return new String(c);
    }

    public CommonPredicateBuilder<T> and(List<CriteriaModel> criterias) {
        if (!CollectionUtils.isEmpty(criterias)) {
            this.criteriaList.addAll(criterias);
        }
        return this;
    }

    public BooleanBuilder build() {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (!CollectionUtils.isEmpty(criteriaList)) {
            List<BooleanExpression> predicates = criteriaList.stream()
                    .map(c -> new CommonPredicate(t, entityVariable).getPredicate(c.getFieldName(), c.getOperation(), c.getValue().toString()))
                    .filter(Objects::nonNull).collect(Collectors.toList());
            for (BooleanExpression predicate : predicates) {
                booleanBuilder = booleanBuilder.and(predicate);
            }
        }
        return booleanBuilder;
    }
}