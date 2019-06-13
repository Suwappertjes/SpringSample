package nl.highway.iungomain.Controllers;

import nl.highway.iungomain.Datamodel.Gebruiker.Gebruiker;
import nl.highway.iungomain.Datamodel.Gebruiker.GebruikerRepository;
import nl.highway.iungomain.Exceptions.BadRequestException;
import nl.highway.iungomain.Exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;


@RestController
@RequestMapping(path = "/iungo/gebruiker")

//TODO: Define access level when Oauth2 is operational.
public class GebruikerController {

    @Autowired
    private GebruikerRepository gebruikerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // GET methods -> Retrieve information.

    @GetMapping
    public @ResponseBody
    Iterable<Gebruiker> getAll() {
        // This returns a JSON or XML with the users
        return gebruikerRepository.findAll();
    }

    @GetMapping(path = "/id/{id}")
    public @ResponseBody Gebruiker findById(@PathVariable Integer id){
        return gebruikerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Gebruikerid","id", id));
    }

    @GetMapping(path = "/gebruikersnaam/{gebruikersnaam}")
    public @ResponseBody Gebruiker findByGebruikersnaam(@PathVariable String gebruikersnaam){
        return gebruikerRepository.findByGebruikersnaam(gebruikersnaam).orElseThrow(() -> new ResourceNotFoundException("Gebruikersnaam","gebruikersnaam", gebruikersnaam));
    }

    @GetMapping(path = "/email/{email}")
    public @ResponseBody Gebruiker findByEmail(@PathVariable String email){
        return gebruikerRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email","email", email));
    }

    // POST methods -> Add new data.

    @PostMapping
    public @ResponseBody ResponseEntity<?> add(@RequestBody Gebruiker gebruiker)
    {

        // Validation.
        if (gebruiker.getId() != null){
            throw new BadRequestException("Please do not supply ID, they are auto-incremented.");
        }

        if (gebruikerRepository.findByGebruikersnaam(gebruiker.getGebruikersnaam()).isPresent()){
            throw new BadRequestException("Gebruikersnaam already in use");
        }

        if (gebruikerRepository.findByEmail(gebruiker.getEmail()).isPresent()){
            throw new BadRequestException("Email already in use.");
        }

        if (gebruiker.getRechten() == null){
            gebruiker.withRechten(new HashSet<>());
        }

        String plainWachtwoord = gebruiker.getWachtwoord();
        gebruiker.withWachtwoord(passwordEncoder.encode(plainWachtwoord));

        gebruikerRepository.save(gebruiker);
        return ResponseEntity.ok("Added new gebruiker to database.");
    }

    // PUT methods -> Replace existing data.

    @PutMapping
    public @ResponseBody ResponseEntity<?> update(@RequestBody Gebruiker gebruiker){
        Integer id = gebruiker.getId();
        Gebruiker storedGebruiker = gebruikerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Gebruikerid","id", id));

        //TODO: Maybe require the old password to be able to change to new one?
        // passwordEncoder.matches("rawPassword", user.getPassword());`

        //TODO: Can the updating of variables be done in a prettier way?

        if (gebruiker.getGebruikersnaam() != null){
            storedGebruiker.withGebruikersnaam(gebruiker.getGebruikersnaam());
        }
        if (gebruiker.getVoornaam() != null){
            storedGebruiker.withVoornaam(gebruiker.getVoornaam());
        }
        if (gebruiker.getAchternaam() != null){
            storedGebruiker.withAchternaam(gebruiker.getAchternaam());
        }
        if (gebruiker.getEmail() != null){
            storedGebruiker.withEmail(gebruiker.getEmail());
        }
        if (gebruiker.getWachtwoord() != null){
            storedGebruiker.withWachtwoord(passwordEncoder.encode(gebruiker.getWachtwoord()));
        }
        if (gebruiker.getRechten() != null){
            storedGebruiker.withRechten(gebruiker.getRechten());
        }

        gebruikerRepository.save(storedGebruiker);
        return ResponseEntity.ok(String.format("Successfully updated Gebruiker with ID: %s.", id));
    }

    //DELETE methods -> Remove data.

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<?> delete(
            @PathVariable Integer id){

        Gebruiker gebruiker = gebruikerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Gebruikerid","id", id));
        gebruikerRepository.delete(gebruiker);
        return ResponseEntity.ok("Successfully deleted from database.");

    }
}