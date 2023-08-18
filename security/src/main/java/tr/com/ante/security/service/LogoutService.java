package tr.com.ante.security.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import tr.com.ante.core.model.ResponseModel;
import tr.com.ante.security.client.AuthenticationClient;
import tr.com.ante.security.model.TokenRecord;

@Component
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    private final AuthenticationClient authenticationClient;

    @SneakyThrows
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final String authHeader = request.getHeader("Authorization");
        final String accessToken;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        accessToken = authHeader.substring(7);
        ResponseModel<Boolean> logoutStatus = authenticationClient.deleteSession(new TokenRecord(accessToken, null));
        SecurityContextHolder.clearContext();

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        response.getWriter().print(new ObjectMapper().writeValueAsString(logoutStatus));
    }
}