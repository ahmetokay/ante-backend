package tr.com.ante.security.accesslog.service;

import jakarta.servlet.http.HttpServletResponse;
import tr.com.ante.core.exception.CriteriaNotSupportedException;
import tr.com.ante.core.model.GenericQueryModel;
import tr.com.ante.core.model.PageModel;
import tr.com.ante.core.service.AbstractGenericService;
import tr.com.ante.enm.ExportTypeEnum;
import tr.com.ante.enm.ReportTypeEnum;
import tr.com.ante.security.accesslog.model.AccessLogModel;
import tr.com.ante.security.accesslog.model.AccessLogQueryModel;
import tr.com.ante.security.user.entity.UserEntity;
import tr.com.ante.security.user.model.UserModel;
import tr.com.ante.security.user.model.UserQueryModel;

public interface AccessLogService {

    PageModel<AccessLogModel> list(GenericQueryModel<AccessLogQueryModel> queryModel) throws CriteriaNotSupportedException;

    void export(String language, ExportTypeEnum exportType, ReportTypeEnum reportType, GenericQueryModel queryModel, HttpServletResponse response);

}