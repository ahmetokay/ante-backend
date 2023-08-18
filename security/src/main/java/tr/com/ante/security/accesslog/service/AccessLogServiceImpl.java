package tr.com.ante.security.accesslog.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import tr.com.ante.core.exception.CriteriaNotSupportedException;
import tr.com.ante.core.exception.ReportException;
import tr.com.ante.core.model.CriteriaModel;
import tr.com.ante.core.model.GenericQueryModel;
import tr.com.ante.core.model.PageModel;
import tr.com.ante.core.utils.PageUtils;
import tr.com.ante.core.utils.ReportUtils;
import tr.com.ante.elastic.model.AccessLogDocument;
import tr.com.ante.elastic.repository.AccessLogRepository;
import tr.com.ante.enm.ExportTypeEnum;
import tr.com.ante.enm.ReportTypeEnum;
import tr.com.ante.security.accesslog.model.AccessLogModel;
import tr.com.ante.security.accesslog.model.AccessLogQueryModel;

import java.io.InputStream;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class AccessLogServiceImpl implements AccessLogService {

    private final AccessLogRepository accessLogRepository;
    private final ResourceLoader resourceLoader;

    @Override
    public PageModel<AccessLogModel> list(GenericQueryModel<AccessLogQueryModel> queryModel) throws CriteriaNotSupportedException {
        Pageable pageable = PageUtils.createPageable(queryModel.getPageable());

        Page<AccessLogDocument> page;
        if (!CollectionUtils.isEmpty(queryModel.getFilters())) {
            Optional<CriteriaModel> userIdOptional = queryModel.getFilters().stream().filter(t -> t.getFieldName().equalsIgnoreCase("userId")).findFirst();
            if (userIdOptional.isPresent()) {
                page = accessLogRepository.findByUserId(Long.parseLong(userIdOptional.get().getValue().toString()), pageable);
            } else {
                page = accessLogRepository.findByUserId(-1L, pageable);
            }
        } else {
            page = accessLogRepository.findAll(pageable);
        }

        PageModel<AccessLogModel> result = new PageModel<>();
        result.setData(page.getContent().stream().map(t -> new AccessLogModel(t.getUserId(), t.getDate(), t.getServletPath(), t.getMethod(), t.getDuration(), t.getRequest(), t.getResponse())).collect(Collectors.toList()));
        result.setTotalElements(page.getTotalElements());
        return result;
    }

    @Override
    public void export(String language, ExportTypeEnum exportType, ReportTypeEnum reportType, GenericQueryModel queryModel, HttpServletResponse response) {
        try {
            InputStream reportStream = resourceLoader.getResource("classpath:/jasper/" + reportType.getSourceName() + language.toUpperCase() + ".jrxml").getInputStream();

            PageModel<AccessLogModel> result = list(queryModel);

            ReportUtils.export(response, reportStream, result.getData(), exportType);
        } catch (Exception e) {
            throw new ReportException("Rapor hazırlanırken hata oluştu.");
        }
    }
}