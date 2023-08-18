package tr.com.ante.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tr.com.ante.auth.model.AuthenticationResponse;
import tr.com.ante.security.model.UserPrincipal;
import tr.com.ante.security.user.model.UserModel;

import java.io.IOException;

public interface AuthenticationService {

    AuthenticationResponse register(UserModel userModel);

    AuthenticationResponse authenticate(UserModel userModel);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

    UserPrincipal getSession(String accessToken);

    UserModel getCurrentUser();

    void logout(String accessToken, String refreshToken);

    boolean hasPermission(String permission);

}