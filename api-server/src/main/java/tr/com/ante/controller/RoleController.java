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
import tr.com.ante.security.role.model.RoleModel;
import tr.com.ante.security.role.model.RoleQueryModel;
import tr.com.ante.security.role.service.RoleService;
import tr.com.ante.test.model.TestQueryModel;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/roles")
public class RoleController {

    private final RoleService service;

    @PreAuthorize("hasPermission(#request, 'RM.01.01')")
    @PostMapping(path = "/list")
    @ResponseBody
    public ResponseModelStructure<PageModel<RoleModel>> list(@RequestBody GenericQueryModel<RoleQueryModel> request) {
        return new ResponseModel<PageModel<RoleModel>>().ok(service.list(request));
    }

    @PreAuthorize("hasPermission(#request, 'RM.01.01')")
    @PostMapping(path = "/list-all")
    @ResponseBody
    public ResponseModelStructure<PageModel<RoleModel>> listAll() {
        return new ResponseModel<PageModel<RoleModel>>().ok(service.list());
    }

    @PreAuthorize("hasPermission(#id, 'RM.01.02')")
    @PostMapping(path = "/role/{id}")
    @ResponseBody
    public ResponseModelStructure<RoleModel> findById(@PathVariable(value = "id") long id) {
        return new ResponseModel<RoleModel>().ok(service.findById(id));
    }

    @PreAuthorize("hasPermission(#dto, 'RM.01.03')")
    @PostMapping(path = "/role")
    @ResponseBody
    public ResponseModelStructure<RoleModel> save(@RequestBody RoleModel dto) {
        return new ResponseModel<RoleModel>().ok(service.save(dto));
    }

    @PreAuthorize("hasPermission(#dto, 'RM.01.04')")
    @PutMapping(path = "/role")
    @ResponseBody
    public ResponseModelStructure<RoleModel> update(@RequestBody RoleModel dto) {
        return new ResponseModel<RoleModel>().ok(service.update(dto));
    }

    @PreAuthorize("hasPermission(#uniqueId, 'RM.01.05')")
    @DeleteMapping(path = "/{uniqueId}")
    @ResponseBody
    public void delete(@PathVariable(value = "uniqueId") String uniqueId) {
        service.deleteByUniqueId(uniqueId);
    }

    @PreAuthorize("hasPermission(null, 'RM.01.06')")
    @PostMapping(value = "/export")
    public void export(HttpServletResponse response, @RequestParam("language") String language, @RequestParam("exportType") ExportTypeEnum exportType, @RequestBody GenericQueryModel<TestQueryModel> request) {
        service.export(language, exportType, ReportTypeEnum.ROLE_MANAGEMENT_DISA_AKTAR, request, response);
    }

    @PreAuthorize("hasPermission(#id, 'RM.01.07')")
    @PostMapping(path = "/history/{id}")
    @ResponseBody
    public ResponseModelStructure<PageModel<RoleModel>> history(@PathVariable(value = "id") Long id) {
        return new ResponseModel<PageModel<RoleModel>>().ok(service.history(id));
    }
}