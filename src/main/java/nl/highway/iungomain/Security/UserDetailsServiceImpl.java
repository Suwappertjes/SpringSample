package nl.highway.iungomain.Security;

import nl.highway.iungomain.Datamodel.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@ComponentScan
@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(user -> new User(
                            user.getUsername(),
                            user.getPassword(),
                            UserRoleAuthority.getAuthorities(user.getRoles())
                    ))
                .orElseThrow(() -> new UsernameNotFoundException("Unknown user: " + username));


    }
}