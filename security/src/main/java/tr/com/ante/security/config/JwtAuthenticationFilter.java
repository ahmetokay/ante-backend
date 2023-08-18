package tr.com.ante.security.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import tr.com.ante.core.exception.SessionExpiredException;
import tr.com.ante.core.model.ResponseModel;
import tr.com.ante.security.client.AuthenticationClient;
import tr.com.ante.security.model.TokenRecord;
import tr.com.ante.security.model.UserPrincipal;

import java.io.IOException;
import java.util.List;

@Order(1)
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationClient authenticationClient;
    private final HandlerExceptionResolver handlerExceptionResolver;
    private final PermittedPathConfiguration permittedPathConfiguration;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        List<String> permittedPathList = permittedPathConfiguration.getPermittedPathList();

        if (permittedPathList.contains(request.getServletPath())) {
            filterChain.doFilter(request, response);
            return;
        }
        final String authHeader = request.getHeader("Authorization");
        final String accessToken;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            SecurityContextHolder.clearContext();
            filterChain.doFilter(request, response);
            return;
        }

        accessToken = authHeader.substring(7);

        try {
            ResponseModel<UserPrincipal> userDetails = authenticationClient.getSession(new TokenRecord(accessToken, null));
            if (!userDetails.isSuccess()) {
                List<String> messageList = userDetails.getMessageList();
                throw new SessionExpiredException(CollectionUtils.isEmpty(messageList) ? "" : messageList.get(0));
            }

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails.getBody(), null, userDetails.getBody().getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);

            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            handlerExceptionResolver.resolveException(request, response, null, ex);
        }
    }
}