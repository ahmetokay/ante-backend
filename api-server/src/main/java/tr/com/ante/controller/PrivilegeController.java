package tr.com.ante.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tr.com.ante.core.model.PageModel;
import tr.com.ante.core.model.ResponseModel;
import tr.com.ante.core.model.ResponseModelStructure;
import tr.com.ante.security.privilege.model.PrivilegeModel;
import tr.com.ante.security.privilege.service.PrivilegeService;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/privileges")
public class PrivilegeController {

    private final PrivilegeService service;

    @PreAuthorize("isAuthenticated()")
    @PostMapping(path = "/list-all")
    @ResponseBody
    public ResponseModelStructure<PageModel<PrivilegeModel>> listAll() {
        return new ResponseModel<PageModel<PrivilegeModel>>().ok(service.list());
    }
}