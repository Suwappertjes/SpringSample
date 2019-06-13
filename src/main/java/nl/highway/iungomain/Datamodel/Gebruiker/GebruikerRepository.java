package nl.highway.iungomain.Datamodel.Gebruiker;

import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository --> not into gebruikerRepository??

public interface GebruikerRepository extends CrudRepository<Gebruiker, Integer> {

    //Todo: make sure the unique identifiers return an optional and have an attached exception when not found.

    Optional<Gebruiker> findByGebruikersnaam(String gebruikersnaam);

    Optional<Gebruiker> findByEmail(String email);
}