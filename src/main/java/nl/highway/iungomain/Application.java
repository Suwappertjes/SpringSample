package nl.highway.iungomain;

import nl.highway.iungomain.Datamodel.User.UserEntity;
import nl.highway.iungomain.Datamodel.User.UserRepository;
import nl.highway.iungomain.Datamodel.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;

@SpringBootApplication
public class Application {


    // Start the application
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner commandLineRunner(UserRepository gebruikersRepository) {
        return args -> {
            if (gebruikersRepository.count() == 0) {
                gebruikersRepository.save(new UserEntity()
                        .withUsername("admin")
                        .withPassword(passwordEncoder.encode("password"))
                        .withFirstName("Admin")
                        .withLastName("Nator")
                        .withEmail("test@admin.com")
                        .withRoles(new HashSet<>(Arrays.asList(RoleName.HIGHWAY, RoleName.BOUWBEDRIJF, RoleName.LEVERANCIER)))
                        //This account gets created if the userRepository is empty and has all the available rights.
                );

                gebruikersRepository.save(new UserEntity()
                                .withUsername("test")
                                .withPassword(passwordEncoder.encode("password"))
                                .withFirstName("Admin")
                                .withLastName("Nator")
                                .withEmail("test@notadmin.com")
                                .withRoles(new HashSet<>(Arrays.asList(RoleName.BOUWBEDRIJF, RoleName.LEVERANCIER)))
                );

            }
        };
    }
}
