package tr.com.ante.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import tr.com.ante.core.utils.DateUtils;
import tr.com.ante.elastic.model.AccessLogDocument;
import tr.com.ante.elastic.repository.AccessLogRepository;
import tr.com.ante.security.config.FilterIgnoredPathConfiguration;
import tr.com.ante.security.model.UserPrincipal;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Order(2)
@Slf4j
@RequiredArgsConstructor
@Component
public class AccessLogFilter extends OncePerRequestFilter {

    @Value("${access-log-enabled}")
    private boolean accessLogEnabled;

    private final ElasticsearchOperations elasticsearchOperations;
    private final AccessLogRepository accessLogRepository;
    private final FilterIgnoredPathConfiguration filterIgnoredPathConfiguration;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        long start = DateUtils.getCurrentDate().getTime();
        filterChain.doFilter(requestWrapper, responseWrapper);
        long end = DateUtils.getCurrentDate().getTime();

        String servletPath = requestWrapper.getServletPath();
        String method = requestWrapper.getMethod();

        boolean ignored = filterIgnoredPathConfiguration.getIgnoredPathList().stream().anyMatch(t -> servletPath.contains(t));
        if (accessLogEnabled && !method.equalsIgnoreCase("OPTIONS") && !ignored) {
            String requestBody = new String(requestWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);
            String responseBody = new String(responseWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getPrincipal() != null && authentication.getPrincipal() instanceof UserPrincipal) {
                UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

                AccessLogDocument accessLogDocument = new AccessLogDocument();
                accessLogDocument.setUniqueId(UUID.randomUUID().toString());
                accessLogDocument.setDate(DateUtils.getCurrentDate());
                accessLogDocument.setUserId(userPrincipal.getUserId());
                accessLogDocument.setServletPath(servletPath);
                accessLogDocument.setMethod(method);
                accessLogDocument.setDuration(end - start);
                accessLogDocument.setRequest(requestBody);
                accessLogDocument.setResponse(responseBody);

                try {
                    IndexOperations indexOperations = elasticsearchOperations.indexOps(AccessLogDocument.class);
                    if (!indexOperations.exists()) {
                        indexOperations.create();
                    }
                    accessLogRepository.save(accessLogDocument);
                } catch (Exception e) {
                    log.error("AccessLogFilter error occurred.", e);
                }
            }
        }

        responseWrapper.copyBodyToResponse();
    }
}