package tr.com.ante.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tr.com.ante.auth.model.AuthenticationResponse;
import tr.com.ante.auth.service.AuthenticationServiceImpl;
import tr.com.ante.core.model.ResponseModel;
import tr.com.ante.security.model.TokenRecord;
import tr.com.ante.security.model.UserPrincipal;
import tr.com.ante.security.user.model.UserModel;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class AuthenticationController {

    private final AuthenticationServiceImpl service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserModel userModel) {
        return ResponseEntity.ok(service.register(userModel));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody UserModel userModel) {
        return ResponseEntity.ok(service.authenticate(userModel));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        service.refreshToken(request, response);
    }

    @PostMapping("/get-session")
    public ResponseModel<UserPrincipal> getSession(@RequestBody TokenRecord tokenRecord) {
        return new ResponseModel<UserPrincipal>().ok(service.getSession(tokenRecord.accessToken()));
    }

    @PostMapping("/get-current-user")
    public ResponseModel<UserModel> getCurrentUser() {
        return new ResponseModel<UserModel>().ok(service.getCurrentUser());
    }

    @PostMapping("/delete-session")
    public ResponseModel<Boolean> deleteSession(@RequestBody TokenRecord tokenRecord) {
        service.logout(tokenRecord.accessToken(), tokenRecord.refreshToken());
        return new ResponseModel<Boolean>().ok(Boolean.TRUE);
    }

    @PostMapping("/has-permission/{permission}")
    public ResponseModel<Boolean> hasPermission(@PathVariable(value = "permission") String permission) {
        return new ResponseModel<Boolean>().ok(service.hasPermission(permission));
    }
}