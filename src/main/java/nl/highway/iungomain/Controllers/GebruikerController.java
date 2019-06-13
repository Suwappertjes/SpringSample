package nl.highway.iungomain.Controllers;

import nl.highway.iungomain.Datamodel.User.UserEntity;
import nl.highway.iungomain.Datamodel.User.UserRepository;
import nl.highway.iungomain.Exceptions.BadRequestException;
import nl.highway.iungomain.Exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;


@RestController
@RequestMapping(path = "/iungo/gebruiker")
public class GebruikerController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // GET methods -> Retrieve information.

    @GetMapping
    public @ResponseBody
    Iterable<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/id/{id}")
    public @ResponseBody
    UserEntity findById(@PathVariable Integer id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Userid","id", id));
    }

    @GetMapping(path = "/username/{username}")
    public @ResponseBody
    UserEntity findByGebruikersnaam(@PathVariable String username){
        return userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Username","username", username));
    }

    @GetMapping(path = "/email/{email}")
    public @ResponseBody
    UserEntity findByEmail(@PathVariable String email){
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email","email", email));
    }

    // POST methods -> Add new data.

    @PostMapping
    public @ResponseBody ResponseEntity<?> add(@RequestBody UserEntity userEntity)
    {

        // Validation.
        if (userEntity.getId() != null){
            throw new BadRequestException("Please do not supply ID, they are auto-incremented.");
        }

        if (userRepository.findByUsername(userEntity.getUsername()).isPresent()){
            throw new BadRequestException("Gebruikersnaam already in use");
        }

        if (userRepository.findByEmail(userEntity.getEmail()).isPresent()){
            throw new BadRequestException("Email already in use.");
        }

        if (userEntity.getRoles() == null){
            userEntity.withRoles(new HashSet<>());
        }

        String plainWachtwoord = userEntity.getPassword();
        userEntity.withPassword(passwordEncoder.encode(plainWachtwoord));

        userRepository.save(userEntity);
        return ResponseEntity.ok("Added new userEntity to database.");
    }

    // PUT methods -> Replace existing data.

    @PutMapping
    public @ResponseBody ResponseEntity<?> update(@RequestBody UserEntity userEntity){
        Integer id = userEntity.getId();
        UserEntity storedUserEntity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Gebruikerid","id", id));

        if (userEntity.getUsername() != null){
            storedUserEntity.withUsername(userEntity.getUsername());
        }
        if (userEntity.getFirstName() != null){
            storedUserEntity.withFirstName(userEntity.getFirstName());
        }
        if (userEntity.getLastName() != null){
            storedUserEntity.withLastName(userEntity.getLastName());
        }
        if (userEntity.getEmail() != null){
            storedUserEntity.withEmail(userEntity.getEmail());
        }
        if (userEntity.getPassword() != null){
            storedUserEntity.withPassword(passwordEncoder.encode(userEntity.getPassword()));
        }
        if (userEntity.getRoles() != null){
            storedUserEntity.withRoles(userEntity.getRoles());
        }

        userRepository.save(storedUserEntity);
        return ResponseEntity.ok(String.format("Successfully updated UserEntity with ID: %s.", id));
    }

    //DELETE methods -> Remove data.

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<?> delete(
            @PathVariable Integer id){

        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Userid","id", id));
        userRepository.delete(userEntity);
        return ResponseEntity.ok("Successfully deleted from database.");

    }
}