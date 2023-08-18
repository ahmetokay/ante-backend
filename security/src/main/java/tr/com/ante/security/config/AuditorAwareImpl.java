package tr.com.ante.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import tr.com.ante.security.model.UserPrincipal;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class AuditorAwareImpl implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        if (SecurityContextHolder.getContext() == null || SecurityContextHolder.getContext().getAuthentication() == null) {
            //if create by system
            return Optional.of(-1L);
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                if (principal instanceof UserPrincipal) {
                    return Optional.of(((UserPrincipal) principal).getUserId());
                }
                return Optional.of(-1L);
            } else {
                return Optional.of(-1L);
            }
        }
    }
}