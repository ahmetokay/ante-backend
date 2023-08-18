package tr.com.ante.core.service;

import jakarta.servlet.http.HttpServletResponse;
import tr.com.ante.core.entity.BaseEntity;
import tr.com.ante.core.exception.CriteriaNotSupportedException;
import tr.com.ante.core.exception.ItemNotFoundException;
import tr.com.ante.core.exception.ReportException;
import tr.com.ante.core.model.BaseModel;
import tr.com.ante.core.model.GenericQueryModel;
import tr.com.ante.core.model.PageModel;
import tr.com.ante.core.model.QueryGeneratorModel;
import tr.com.ante.enm.ExportTypeEnum;
import tr.com.ante.enm.ReportTypeEnum;

import java.io.IOException;

public interface AbstractGenericService<D extends BaseModel, E extends BaseEntity, QM extends QueryGeneratorModel> {

    D save(D dto);

    D update(D dto) throws ItemNotFoundException;

    void deleteByUniqueId(String uniqueId) throws ItemNotFoundException;

    D findById(Long id) throws ItemNotFoundException;

    PageModel<D> history(Long id);

    PageModel<D> list();

    PageModel<D> list(GenericQueryModel queryModel) throws CriteriaNotSupportedException;

    PageModel<D> listWithoutPaging(GenericQueryModel queryModel) throws CriteriaNotSupportedException;

    void export(String language, ExportTypeEnum exportType, ReportTypeEnum reportType, GenericQueryModel queryModel, HttpServletResponse response);

    void exportWithoutPaging(String reportName, GenericQueryModel queryModel, HttpServletResponse response) throws CriteriaNotSupportedException, ReportException;

}