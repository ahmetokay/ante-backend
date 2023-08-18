package tr.com.ante.test.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import tr.com.ante.core.model.GenericQueryModel;
import tr.com.ante.core.service.AbstractReportService;

@Component
public class TestReportServiceImpl implements AbstractReportService {

    @Override
    public void createDetailReport(GenericQueryModel queryModel, HttpServletResponse response) {
    }

    @Override
    public void createListReport(GenericQueryModel queryModel, HttpServletResponse response) {
    }
}