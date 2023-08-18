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
import tr.com.ante.security.user.model.UserModel;
import tr.com.ante.security.user.model.UserQueryModel;
import tr.com.ante.security.user.service.UserService;
import tr.com.ante.test.model.TestQueryModel;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService service;

    @PreAuthorize("hasPermission(#request, 'UM.01.01')")
    @PostMapping(path = "/list")
    @ResponseBody
    public ResponseModelStructure<PageModel<UserModel>> list(@RequestBody GenericQueryModel<UserQueryModel> request) {
        return new ResponseModel<PageModel<UserModel>>().ok(service.list(request));
    }

    @PreAuthorize("hasPermission(#id, 'UM.01.02')")
    @PostMapping(path = "/user/{id}")
    @ResponseBody
    public ResponseModelStructure<UserModel> findById(@PathVariable(value = "id") long id) {
        return new ResponseModel<UserModel>().ok(service.findById(id));
    }

    @PreAuthorize("hasPermission(#dto, 'UM.01.03')")
    @PostMapping(path = "/user")
    @ResponseBody
    public ResponseModelStructure<UserModel> save(@RequestBody UserModel dto) {
        return new ResponseModel<UserModel>().ok(service.save(dto));
    }

    @PreAuthorize("hasPermission(#dto, 'UM.01.04')")
    @PutMapping(path = "/user")
    @ResponseBody
    public ResponseModelStructure<UserModel> update(@RequestBody UserModel dto) {
        return new ResponseModel<UserModel>().ok(service.update(dto));
    }

    @PreAuthorize("hasPermission(#uniqueId, 'UM.01.05')")
    @DeleteMapping(path = "/{uniqueId}")
    @ResponseBody
    public void delete(@PathVariable(value = "uniqueId") String uniqueId) {
        service.deleteByUniqueId(uniqueId);
    }

    @PreAuthorize("hasPermission(null, 'UM.01.06')")
    @PostMapping(value = "/export")
    public void export(HttpServletResponse response, @RequestParam("language") String language, @RequestParam("exportType") ExportTypeEnum exportType, @RequestBody GenericQueryModel<TestQueryModel> request) {
        service.export(language, exportType, ReportTypeEnum.USER_MANAGEMENT_DISA_AKTAR, request, response);
    }

    @PreAuthorize("hasPermission(#id, 'UM.01.07')")
    @PostMapping(path = "/history/{id}")
    @ResponseBody
    public ResponseModelStructure<PageModel<UserModel>> history(@PathVariable(value = "id") Long id) {
        return new ResponseModel<PageModel<UserModel>>().ok(service.history(id));
    }

    @PostMapping(path = "/reset-password")
    @ResponseBody
    public ResponseModelStructure resetPassword(@RequestBody UserModel dto) {
        service.resetPassword(dto);
        return new ResponseModel().ok();
    }
}