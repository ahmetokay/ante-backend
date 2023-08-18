package tr.com.ante.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import tr.com.ante.core.exception.UserNotFoundException;
import tr.com.ante.security.model.UserPrincipal;
import tr.com.ante.security.privilege.entity.PrivilegeEntity;
import tr.com.ante.security.role.entity.RoleEntity;
import tr.com.ante.security.user.entity.UserEntity;
import tr.com.ante.security.user.repository.UserRepository;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Primary
@Component
public class ApplicationUserDetailsService implements UserDetailsService {

    public static Collection<SimpleGrantedAuthority> grantedAuthorities;
    private final UserRepository userRepository;

    @Override
    public UserPrincipal loadUserByUsername(String email) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(MessageFormat.format("Sistemde {0} kayıtlı kullanıcı adı bulunamadı.", email)));

        grantedAuthorities = getAuthorities(user.getRoleList());
        return new UserPrincipal(user, grantedAuthorities);
    }

    private List<SimpleGrantedAuthority> getAuthorities(Collection<RoleEntity> roleList) {
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(roleList)) {
            List<PrivilegeEntity> privilegeList = new ArrayList<>();

            roleList.stream().forEach(roleEntity -> privilegeList.addAll(roleEntity.getPrivilegeList()));

            if (!CollectionUtils.isEmpty(privilegeList)) {
                List<PrivilegeEntity> parentPrivilegeList = privilegeList.stream().filter(privilegeEntity -> privilegeEntity.getName().contains("*")).collect(Collectors.toList());
                parentPrivilegeList.stream().forEach(parent -> privilegeList.removeIf(privilege -> privilege.getParentId() != null && privilege.getParentId().equals(parent.getId())));

                for (PrivilegeEntity privilegeEntity : privilegeList) {
                    authorityList.add(new SimpleGrantedAuthority(privilegeEntity.getName()));
                }
            }
        }

        return authorityList;
    }
}