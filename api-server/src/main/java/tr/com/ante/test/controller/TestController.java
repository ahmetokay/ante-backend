package tr.com.ante.test.controller;

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
import tr.com.ante.test.model.TestModel;
import tr.com.ante.test.model.TestQueryModel;
import tr.com.ante.test.service.TestService;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/test")
public class TestController {

    private final TestService service;

    @PreAuthorize("hasPermission(#request, 'UM.01.01')")
    @PostMapping(path = "/list")
    @ResponseBody
    public ResponseModelStructure<PageModel<TestModel>> list(@RequestBody GenericQueryModel<TestQueryModel> request) {
        return new ResponseModel<PageModel<TestModel>>().ok(service.list(request));
    }

    @PreAuthorize("hasPermission(null, 'UM.01.06')")
    @PostMapping(value = "/export")
    public void export(HttpServletResponse response, @RequestParam("language") String language, @RequestParam("exportType") ExportTypeEnum exportType, @RequestBody GenericQueryModel<TestQueryModel> request) {
        service.export(language, exportType, ReportTypeEnum.TEST_DISA_AKTAR, request, response);
    }
}