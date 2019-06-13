package nl.highway.iungomain.Security;

import nl.highway.iungomain.Datamodel.RechtNaam;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public class UserRoleAuthority implements GrantedAuthority {
    private final RechtNaam recht;

    public UserRoleAuthority(RechtNaam recht) {
        this.recht = recht;
    }

    public static Set<UserRoleAuthority> getAuthorities(Set<RechtNaam> rechten) {
        return rechten.stream().map(UserRoleAuthority::new).collect(Collectors.toSet());
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + recht.name();
    }
}