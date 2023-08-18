package tr.com.ante.core.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.*;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import tr.com.ante.core.entity.BaseEntity;
import tr.com.ante.core.exception.CriteriaNotSupportedException;
import tr.com.ante.core.model.CriteriaOperationType;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonPredicate<T extends BaseEntity> {

    private final Class<? extends T> clazz;
    private final String entityVariable;

    public CommonPredicate(Class<? extends T> clazz, String entityVariable) {
        this.clazz = clazz;
        this.entityVariable = entityVariable;
    }

    public BooleanExpression getPredicate(String fieldName, CriteriaOperationType operator, String value) {
        PathBuilder<T> entityPath = new PathBuilder<>(clazz, entityVariable);
        return getPredicate(fieldName, operator, value, entityPath, clazz);
    }

    private BooleanExpression getPredicate(String fieldName, CriteriaOperationType operator, String value, PathBuilder<?> entityPath, Class<?> classType) {
        boolean isMultiValue = value.contains(",");
        try {
            Class<?> propertyType = getPropertyType(classType, fieldName);
            switch (propertyType.getSimpleName()) {
                case "Integer":
                    if (isMultiValue) {
                        NumberPath<Integer> path = entityPath.getNumber(fieldName, Integer.class);
                        Integer[] numValue = Stream.of(value.split(",")).map(Integer::parseInt).toArray(Integer[]::new);
                        return getNumberPredicate(path, operator, numValue);
                    } else {
                        NumberPath<Integer> path = entityPath.getNumber(fieldName, Integer.class);
                        Integer numValue = Integer.parseInt(value);
                        return getNumberPredicate(path, operator, numValue);
                    }
                case "Long":
                    if (isMultiValue) {
                        NumberPath<Long> path = entityPath.getNumber(fieldName, Long.class);
                        Long[] numValue = Stream.of(value.split(",")).map(Long::parseLong).toArray(Long[]::new);
                        return getNumberPredicate(path, operator, numValue);
                    } else {
                        NumberPath<Long> path = entityPath.getNumber(fieldName, Long.class);
                        Long numValue = Long.parseLong(value);
                        return getNumberPredicate(path, operator, numValue);
                    }
                case "Double":
                    if (isMultiValue) {
                        NumberPath<Double> path = entityPath.getNumber(fieldName, Double.class);
                        Double[] numValue = Stream.of(value.split(",")).map(Double::parseDouble).toArray(Double[]::new);
                        return getNumberPredicate(path, operator, numValue);
                    } else {
                        NumberPath<Double> path = entityPath.getNumber(fieldName, Double.class);
                        Double numValue = Double.parseDouble(value);
                        return getNumberPredicate(path, operator, numValue);
                    }
                case "List":
                    BooleanBuilder booleanBuilder = new BooleanBuilder();

                    Field field = clazz.getDeclaredField(fieldName);
                    ParameterizedType listType = (ParameterizedType) field.getGenericType();
                    Class contentClass = (Class) listType.getActualTypeArguments()[0];

                    ListPath path = entityPath.getList(fieldName, contentClass);

                    if (StringUtils.hasText(value)) {
                        Stream.of(value.split(",")).map(Long::parseLong).forEach(id -> {
                            try {
                                T instance = (T) contentClass.newInstance();
                                instance.setId(id);

                                booleanBuilder.and(path.contains(instance));
                            } catch (Exception e) {
                            }
                        });
                    }

                    return booleanBuilder.hasValue() ? Expressions.asBoolean(booleanBuilder) : Expressions.asBoolean(true).isTrue();
                case "Boolean":
                    if (CriteriaOperationType.equals.equals(operator)) {
                        return entityPath.getBoolean(fieldName).eq(Boolean.parseBoolean(value));
                    } else {
                        throw new RuntimeException("Unsupported Boolean operation");
                    }
                case "boolean":
                    if (CriteriaOperationType.equals.equals(operator)) {
                        return entityPath.getBoolean(fieldName).eq(Boolean.parseBoolean(value));
                    } else {
                        throw new RuntimeException("Unsupported Boolean operation");
                    }
                case "String":
                    return getStringPredicate(fieldName, operator, value, entityPath);
                case "Date":
                    return getDatePredicate(fieldName, operator, value, entityPath);
                case "LocalDate":
                    return getDatePredicate(fieldName, operator, value, entityPath);
                case "LocalDateTime":
                    return getDateTimePredicate(fieldName, operator, value, entityPath);
                default:
                    return null;
            }
        } catch (Exception e) {
            throw new CriteriaNotSupportedException("Lütfen girilen filtre değerlerini kontrol ediniz.");
        }
    }

    private BooleanExpression getNumberPredicate(NumberPath<Integer> path, CriteriaOperationType operator, Integer value) {
        switch (operator) {
            case equals:
                return path.eq(value);
            case gt:
                return path.gt(value);
            case lt:
                return path.lt(value);
            case gte:
                return path.goe(value);
            case lte:
                return path.loe(value);
            default:
                return null;
        }
    }

    private BooleanExpression getNumberPredicate(NumberPath<Double> path, CriteriaOperationType operator, Double value) {
        switch (operator) {
            case equals:
                return path.eq(value);
            case gt:
                return path.gt(value);
            case lt:
                return path.lt(value);
            case gte:
                return path.goe(value);
            case lte:
                return path.loe(value);
            default:
                return null;
        }
    }

    private BooleanExpression getNumberPredicate(NumberPath<Long> path, CriteriaOperationType operator, Long value) {
        switch (operator) {
            case equals:
                return path.eq(value);
            case gt:
                return path.gt(value);
            case lt:
                return path.lt(value);
            case gte:
                return path.goe(value);
            case lte:
                return path.loe(value);
            default:
                return null;
        }
    }

    private BooleanExpression getNumberPredicate(NumberPath<?> path, CriteriaOperationType operator, Object[] numValue) {
        Number[] valueArray = (Number[]) numValue;
        switch (operator) {
            case in:
                return path.in(valueArray);
            default:
                return null;
        }
    }

    private BooleanExpression getDatePredicate(String key, CriteriaOperationType operator, String value, PathBuilder<?> entityPath) {
        DatePath<LocalDate> path = entityPath.getDate(key, LocalDate.class);
        if (value.contains(",")) {
            List<LocalDate> dateValues = Stream.of(value.split(",")).map(LocalDate::parse).collect(Collectors.toList());
            switch (operator) {
                case in:
                    return path.in(dateValues);
                case equals:
                    return path.between(dateValues.get(0), dateValues.get(1));
                default:
                    return null;
            }
        } else {
            LocalDate dateValue = LocalDate.parse(value);
            switch (operator) {
                case equals:
                    return path.eq(dateValue);
                case gt:
                    return path.gt(dateValue);
                case lt:
                    return path.lt(dateValue);
                case gte:
                    return path.goe(dateValue);
                case lte:
                    return path.loe(dateValue);
                default:
                    return null;
            }
        }
    }

    private BooleanExpression getDateTimePredicate(String key, CriteriaOperationType operator, String value, PathBuilder<?> entityPath) {
        DatePath<LocalDateTime> path = entityPath.getDate(key, LocalDateTime.class);
        if (value.contains(",")) {
            List<LocalDate> dateValues = Stream.of(value.split(",")).map(LocalDate::parse).collect(Collectors.toList());
            switch (operator) {
                case equals:
                    return path.in(dateValues.parallelStream().map(LocalDate::atStartOfDay).collect(Collectors.toList()));
                default:
                    return null;
            }
        } else {
            LocalDate dateValue = LocalDate.parse(value);
            switch (operator) {
                case equals:
                    return path.between(dateValue.atStartOfDay(), dateValue.atTime(LocalTime.MAX));
                case gt:
                    return path.gt(dateValue.atTime(LocalTime.MAX));
                case lt:
                    return path.lt(dateValue.atStartOfDay());
                case gte:
                    return path.goe(dateValue.atStartOfDay());
                case lte:
                    return path.loe(dateValue.atTime(LocalTime.MAX));
                default:
                    return null;
            }
        }
    }

    private BooleanExpression getStringPredicate(String key, CriteriaOperationType operator, String value, PathBuilder<?> entityPath) {
        StringPath path = entityPath.getString(key);
        if (value.contains(",")) {
            return path.in(Stream.of(value.split(",")).collect(Collectors.toList()));
        }
        switch (operator) {
            case equals:
                return path.equalsIgnoreCase(value);
            case notEquals:
                return path.notEqualsIgnoreCase(value);
            case contains:
                return path.containsIgnoreCase(value);
            case startsWith:
                return path.startsWithIgnoreCase(value);
            case endsWith:
                return path.endsWithIgnoreCase(value);
            default:
                return null;
        }
    }

    private Class<?> getPropertyType(Class<?> parent, String property) throws NoSuchFieldException {
        List<String> propertyList = new LinkedList<>(Arrays.asList(property.split("\\.")));
        return getRecursiveType(parent, propertyList);
    }

    private Class<?> getRecursiveType(Class<?> parent, List<String> propertyList) throws NoSuchFieldException {
        if (propertyList.size() > 1) {
            Field field = parent.getDeclaredField(propertyList.get(0));
            Class<?> child = field.getType();
            propertyList.remove(propertyList.get(0));
            if ("List".equals(child.getSimpleName())) {
                return child;
            }
            return getRecursiveType(child, propertyList);
        } else {
            return ReflectionUtils.findField(parent, propertyList.get(0)).getType();
        }
    }
}