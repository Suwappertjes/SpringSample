package nl.highway.iungomain;

import nl.highway.iungomain.Datamodel.Gebruiker.Gebruiker;
import nl.highway.iungomain.Datamodel.Gebruiker.GebruikerRepository;
import nl.highway.iungomain.Datamodel.RechtNaam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
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
    public CommandLineRunner commandLineRunner(GebruikerRepository gebruikersRepository) {
        return args -> {
            if (gebruikersRepository.count() == 0) {
                String password = "password";
                gebruikersRepository.save(new Gebruiker()
                        .withGebruikersnaam("admin")
                        .withWachtwoord(passwordEncoder.encode(password))
                        .withVoornaam("Admin")
                        .withAchternaam("Nator")
                        .withEmail("test@admin.com")
                        .withRechten(new HashSet<>(Arrays.asList(RechtNaam.HIGHWAY, RechtNaam.BOUWBEDRIJF, RechtNaam.LEVERANCIER)))
                        //This account gets created if the gebruikersRepository is empty and has all the available rights.
                );

                gebruikersRepository.save(new Gebruiker()
                                .withGebruikersnaam("test")
                                .withWachtwoord(passwordEncoder.encode("password"))
                                .withVoornaam("Admin")
                                .withAchternaam("Nator")
                                .withEmail("test@notadmin.com")
                                .withRechten(new HashSet<>(Arrays.asList(RechtNaam.BOUWBEDRIJF, RechtNaam.LEVERANCIER)))
                );

            }
        };
    }


    //TODO: Json mapping to be able to write code in english.
}
