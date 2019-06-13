package nl.highway.iungomain.Security;

import nl.highway.iungomain.Datamodel.Gebruiker.GebruikerRepository;
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


    private final GebruikerRepository gebruikerRepository;

    @Autowired
    public UserDetailsServiceImpl(GebruikerRepository gebruikerRepository) {
        this.gebruikerRepository = gebruikerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String gebruikersNaam) {
        return gebruikerRepository.findByGebruikersnaam(gebruikersNaam)
                .map(gebruiker -> {
                    System.out.print(gebruiker.getWachtwoord());
                    return new User(
                            gebruiker.getGebruikersnaam(),
                            gebruiker.getWachtwoord(),
                            UserRoleAuthority.getAuthorities(gebruiker.getRechten())
                    );
                })
                .orElseThrow(() -> new UsernameNotFoundException("Unknown user: " + gebruikersNaam));


    }
}