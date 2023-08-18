package tr.com.ante.security.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tr.com.ante.security.databind.CustomAuthorityDeserializer;
import tr.com.ante.security.user.entity.UserEntity;

import java.util.ArrayList;
import java.util.Collection;

public class UserPrincipal implements UserDetails {

    private final long userId;
    private final UserEntity user;
    private final Collection<SimpleGrantedAuthority> authorities;

    public UserPrincipal(UserEntity user, @JsonDeserialize(using = CustomAuthorityDeserializer.class) Collection<SimpleGrantedAuthority> authorities) {
        this.user = user;
        this.userId = user.getId();
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>(authorities);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserEntity getUser() {
        return user;
    }

    public long getUserId() {
        return userId;
    }

}