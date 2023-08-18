package tr.com.ante.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import tr.com.ante.core.exception.ResourceForbiddenExpiredException;
import tr.com.ante.security.model.UserPrincipal;
import tr.com.ante.security.utils.PermissionUtils;

import java.io.Serializable;
import java.util.stream.Collectors;

@Slf4j
public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        log.info("User {} with permission {}", userPrincipal.getUsername(), permission.toString());

        boolean hasPermission = PermissionUtils.checkPermission(userPrincipal.getAuthorities().stream().map(t -> t.getAuthority()).collect(Collectors.toList()), permission.toString());
        if (!hasPermission) {
            throw new ResourceForbiddenExpiredException("Bu servis için erişim yetkiniz yoktur.");
        }
        return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}