package tr.com.ante.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tr.com.ante.core.model.GenericQueryModel;
import tr.com.ante.core.model.PageModel;
import tr.com.ante.core.model.ResponseModel;
import tr.com.ante.core.model.ResponseModelStructure;
import tr.com.ante.enm.ExportTypeEnum;
import tr.com.ante.enm.ReportTypeEnum;
import tr.com.ante.security.accesslog.model.AccessLogModel;
import tr.com.ante.security.accesslog.model.AccessLogQueryModel;
import tr.com.ante.security.accesslog.service.AccessLogService;
import tr.com.ante.security.user.model.UserQueryModel;
import tr.com.ante.test.model.TestQueryModel;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/access-log")
public class AccessLogController {

    private final AccessLogService service;

    @PreAuthorize("hasPermission(#request, 'AL.01.01')")
    @PostMapping(path = "/list")
    @ResponseBody
    public ResponseModelStructure<PageModel<AccessLogModel>> list(@RequestBody GenericQueryModel<AccessLogQueryModel> request) {
        return new ResponseModel<PageModel<AccessLogModel>>().ok(service.list(request));
    }

    @PreAuthorize("hasPermission(null, 'AL.01.01')")
    @PostMapping(value = "/export")
    public void export(HttpServletResponse response, @RequestParam("language") String language, @RequestParam("exportType") ExportTypeEnum exportType, @RequestBody GenericQueryModel<TestQueryModel> request) {
        service.export(language, exportType, ReportTypeEnum.ACCESS_LOG_DISA_AKTAR, request, response);
    }
}