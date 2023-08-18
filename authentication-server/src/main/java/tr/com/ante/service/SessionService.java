package tr.com.ante.service;

import org.springframework.security.core.userdetails.UserDetails;
import tr.com.ante.security.model.UserPrincipal;

public interface SessionService {

    boolean hasSession(String token);
    UserPrincipal getSession(String token);

    void createSession(String accessToken, String refreshToken, UserDetails userDetails);

    void logoutSession(String accessToken, String refreshToken);

}