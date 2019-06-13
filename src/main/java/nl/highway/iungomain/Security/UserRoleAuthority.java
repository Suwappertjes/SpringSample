package nl.highway.iungomain.Security;

import nl.highway.iungomain.Datamodel.RoleName;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public class UserRoleAuthority implements GrantedAuthority {
    private final RoleName role;

    public UserRoleAuthority(RoleName roles) {
        this.role = roles;
    }

    public static Set<UserRoleAuthority> getAuthorities(Set<RoleName> role) {
        return role.stream().map(UserRoleAuthority::new).collect(Collectors.toSet());
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + role.name();
    }
}