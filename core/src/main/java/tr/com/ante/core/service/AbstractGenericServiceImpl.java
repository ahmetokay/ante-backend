package tr.com.ante.core.service;

import com.querydsl.core.BooleanBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Query;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.NotNull;
import org.apache.commons.collections4.IterableUtils;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tr.com.ante.core.converter.BaseConverter;
import tr.com.ante.core.entity.BaseEntity;
import tr.com.ante.core.exception.BaseException;
import tr.com.ante.core.exception.CriteriaNotSupportedException;
import tr.com.ante.core.exception.ItemNotFoundException;
import tr.com.ante.core.exception.ReportException;
import tr.com.ante.core.manager.AbstractOperationalManager;
import tr.com.ante.core.manager.AbstractSearchManager;
import tr.com.ante.core.model.BaseModel;
import tr.com.ante.core.model.GenericQueryModel;
import tr.com.ante.core.model.PageModel;
import tr.com.ante.core.model.QueryGeneratorModel;
import tr.com.ante.core.predicate.CommonPredicateBuilder;
import tr.com.ante.core.query.AbstractQueryGenerator;
import tr.com.ante.core.utils.GenericReportUtils;
import tr.com.ante.core.utils.PageUtils;
import tr.com.ante.core.utils.ReportUtils;
import tr.com.ante.enm.ExportTypeEnum;
import tr.com.ante.enm.ReportTypeEnum;

import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public abstract class AbstractGenericServiceImpl<D extends BaseModel, E extends BaseEntity, QM extends QueryGeneratorModel> implements AbstractGenericService<D, E, QM> {

    private EntityManager entityManager;

    private ResourceLoader resourceLoader;

    protected Class<E> entityClass;
    protected Class<D> dtoClass;
    protected Class<QM> queryGeneratorClass;

    protected abstract BaseConverter<D, E> getConverter();

    protected abstract AbstractSearchManager<E> getSearchManager();

    protected abstract AbstractOperationalManager<E> getOperationalManager();

    protected abstract AbstractQueryGenerator<E, QM> getQueryGenerator();

    @PostConstruct
    public void init() {
        this.dtoClass = (Class<D>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        this.queryGeneratorClass = (Class<QM>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2];
    }

    @Transactional
    @Override
    public D save(D dto) {
        E mappedEntity = getConverter().toEntity(dto);
        return getConverter().toModel(getOperationalManager().save(mappedEntity));
    }

    @Transactional
    @Override
    public D update(D dto) throws ItemNotFoundException {
        if (dto.getId() == null) {
            throw new ItemNotFoundException("Guncellenecek kayit bulunamadi.");
        }

        E mappedEntity = getConverter().toEntity(dto);
        return getConverter().toModel(getOperationalManager().update(mappedEntity));
    }

    @Transactional
    @Override
    public void deleteByUniqueId(String uniqueId) throws ItemNotFoundException {
        getOperationalManager().deleteByUniqueId(uniqueId);
    }

    @Transactional(readOnly = true)
    @Override
    public D findById(Long id) throws ItemNotFoundException {
        E entity = getSearchManager().findById(id);
        return getConverter().toModel(entity);
    }

    @Override
    public PageModel<D> history(Long id) {
        AuditReader reader = AuditReaderFactory.get(entityManager);
        if (reader.isEntityNameAudited(entityClass.getName())) {
            AuditQuery query = reader.createQuery().forRevisionsOfEntity(entityClass, false, true);
            query.add(AuditEntity.id().eq(id));
            query.addOrder(AuditEntity.revisionNumber().desc());

            List<D> modelList = new ArrayList<>();

            List resultList = query.getResultList();
            resultList.stream().forEach(object -> {
                Object[] objectArr = (Object[]) object;
                E entity = (E) objectArr[0];
                DefaultRevisionEntity auditRevisions = (DefaultRevisionEntity) objectArr[1];
                RevisionType revisionType = (RevisionType) objectArr[2];

//                try {
//                    fillNestedAudits(reader, entity, auditRevisions.getId());
//                } catch (IllegalAccessException e) {
//                    throw new RuntimeException(e);
//                }

                D model = getConverter().toModel(entity);
                model.setRevisionType(revisionType.name());

                modelList.add(model);
            });

            PageModel<D> result = new PageModel<>();
            result.setData(modelList);
            result.setTotalElements(modelList.size());
            return result;
        }

        return new PageModel<>();
    }

    private void fillNestedAudits(AuditReader reader, E entity, int id) throws IllegalAccessException {
        Field[] declaredFields = entity.getClass().getDeclaredFields();

        for (Field declaredField : declaredFields) {
            if(declaredField.getType().equals(List.class)){

                ParameterizedType stringListType = (ParameterizedType) declaredField.getGenericType();
                Class<?> stringListClass = (Class<?>) stringListType.getActualTypeArguments()[0];

                Annotation[] declaredAnnotations = declaredField.getDeclaredAnnotations();
                for (Annotation declaredAnnotation : declaredAnnotations) {
                    if(declaredAnnotation.annotationType().equals(JoinTable.class)){
                        JoinTable annotation = declaredField.getAnnotation(JoinTable.class);
                        String tableName = annotation.name() + "_audit";
                        JoinColumn joinColumn = annotation.inverseJoinColumns()[0];
                        String referencedColumnName = joinColumn.referencedColumnName();

                        StringBuilder sb = new StringBuilder();
                        sb.append("SELECT fk_role_id, fk_privilege_id, revtype FROM ");
                        sb.append(tableName).append(" where rev=").append(id);

                        List<Object> revObjList = new ArrayList<>();
                        Query nativeQuery = entityManager.createNativeQuery(sb.toString());
                        List<Object[]> resultList1 = nativeQuery.getResultList();
                        resultList1.stream().forEach(row -> {

                            Object ent = entityManager.find(stringListClass, row[1]);
                            Field[] declaredFields1 = ((Class) stringListClass.getGenericSuperclass()).getDeclaredFields();
                            for (Field field : declaredFields1) {
                                if(field.getName().equals("revisionType")){
                                    field.setAccessible(true);
                                    try {
                                        field.set(ent, RevisionType.fromRepresentation(((Short) row[2]).byteValue()).name());
                                    } catch (IllegalAccessException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }

                            revObjList.add(ent);
                            System.out.println();
                        });

                        declaredField.setAccessible(true);
                        declaredField.set(entity, revObjList);

                        System.out.println("dşşş");
                    }
                    System.out.println("dsadsa");
                }

                System.out.println("ççç");
            }
        }

        System.out.println(declaredFields);
    }

    public static <T> void copyAvalableFields(@NotNull T source, @NotNull T target) throws IllegalAccessException {
        Field[] fields = source.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (!Modifier.isStatic(field.getModifiers()) && !Modifier.isFinal(field.getModifiers())) {
                field.set(target, field.get(source));
            }
        }
    }

    @Override
    public PageModel<D> list() {
        List<E> entityList = getSearchManager().list();
        if (!CollectionUtils.isEmpty(entityList)) {
            List<D> modelList = getConverter().toModelList(entityList);

            PageModel<D> result = new PageModel<>();
            result.setData(modelList);
            result.setTotalElements(modelList.size());
            return result;
        }
        return new PageModel<>();
    }

    @Override
    public PageModel<D> list(GenericQueryModel queryModel) throws CriteriaNotSupportedException {
        Pageable pageable = PageUtils.createPageable(queryModel.getPageable());

        BooleanBuilder builder = createBooleanBuilder(queryModel);

        Page<E> page = getSearchManager().list(builder, pageable);

        PageModel<D> result = new PageModel<>();
        result.setData(getConverter().toModelList(page.getContent()));
        result.setTotalElements(page.getTotalElements());
        return result;
    }

    @Override
    public PageModel<D> listWithoutPaging(GenericQueryModel queryModel) throws CriteriaNotSupportedException {
        BooleanBuilder builder = createBooleanBuilder(queryModel);

        Iterable<E> iterable = getSearchManager().list(builder);

        List<E> entityList = IterableUtils.toList(iterable);

        PageModel<D> result = new PageModel<>();
        result.setData(getConverter().toModelList(entityList));
        result.setTotalElements(entityList.size());
        return result;
    }

    @Override
    public void export(String language, ExportTypeEnum exportType, ReportTypeEnum reportType, GenericQueryModel queryModel, HttpServletResponse response) {
        try {
            InputStream reportStream = resourceLoader.getResource("classpath:/jasper/" + reportType.getSourceName() + language.toUpperCase() + ".jrxml").getInputStream();

            PageModel<D> result = listWithoutPaging(queryModel);

            ReportUtils.export(response, reportStream, result.getData(), exportType);
        } catch (Exception e) {
            throw new ReportException("Rapor hazırlanırken hata oluştu.");
        }
    }

    @Override
    public void exportWithoutPaging(String reportName, GenericQueryModel queryModel, HttpServletResponse response) throws CriteriaNotSupportedException, ReportException {
        BooleanBuilder builder = createBooleanBuilder(queryModel);
        Iterable<E> entityList = getSearchManager().list(builder);

        GenericReportUtils.export(reportName, dtoClass, getConverter().toModelList(IterableUtils.toList(entityList)), response);
    }

    private BooleanBuilder createBooleanBuilder(GenericQueryModel queryModel) {
        BooleanBuilder builder = new CommonPredicateBuilder<>(entityClass).and(queryModel.getFilters()).build();

        if (queryModel.getQuery() != null) {
            if (getQueryGenerator() == null) {
                throw new BaseException("QueryGenerator class kontrol ediniz.");
            }

            getQueryGenerator().generateQuery(builder, (QM) queryModel.getQuery());
        }

        return builder;
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}