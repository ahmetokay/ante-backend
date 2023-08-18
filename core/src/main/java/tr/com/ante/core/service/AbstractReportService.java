package tr.com.ante.core.service;

import jakarta.servlet.http.HttpServletResponse;
import tr.com.ante.core.entity.BaseEntity;
import tr.com.ante.core.model.GenericQueryModel;

public interface AbstractReportService<E extends BaseEntity> {

    void createDetailReport(GenericQueryModel queryModel, HttpServletResponse response);

    void createListReport(GenericQueryModel queryModel, HttpServletResponse response);

}