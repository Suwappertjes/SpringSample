package nl.highway.iungomain.Controllers;

import nl.highway.iungomain.Datamodel.Bouwbedrijf.Bouwbedrijf;
import nl.highway.iungomain.Datamodel.Bouwbedrijf.BouwbedrijfRepository;
import nl.highway.iungomain.Exceptions.BadRequestException;
import nl.highway.iungomain.Exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path="/iungo/bouwbedrijf")
//TODO: Define access level when Oauth2 is operational.
//@PreAuthorize("#oauth2.hasScope('read')")
public class BouwbedrijfController {

    @Autowired
    private BouwbedrijfRepository bouwbedrijfRepository;

    // GET methods -> Retrieve information.

    @GetMapping
    public @ResponseBody Iterable<Bouwbedrijf> getAll() {
        // This returns a JSON or XML with the users
        return bouwbedrijfRepository.findAll();
    }

    @GetMapping(path = "/id/{id}")
    public @ResponseBody Bouwbedrijf findById(@PathVariable Integer id) {
        return bouwbedrijfRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bouwbedrijfid","id", id));
    }

    @GetMapping(path = "/kvk/{kvk}")
    public @ResponseBody Bouwbedrijf findByKvk(@PathVariable Integer kvk) {
        return bouwbedrijfRepository.findByKvk(kvk).orElseThrow(() -> new ResourceNotFoundException("Bouwbedrijfkvk","kvk", kvk));
    }


    // POST methods -> Add new data.

    @PostMapping
    public @ResponseBody ResponseEntity<?> add(@RequestBody Bouwbedrijf bouwbedrijf)
    {

        // Validation.
        if (bouwbedrijf.getId() != null){
            throw new BadRequestException("Please do not supply ID, they are auto-incremented.");
        }

        // Validation.
        if (bouwbedrijfRepository.findByKvk(bouwbedrijf.getKvk()).isPresent()){
            throw new BadRequestException("Kvk-number already in use.");
        }


        bouwbedrijfRepository.save(bouwbedrijf);
        return ResponseEntity.ok("Added new bouwbedrijf to database.");
    }

    // PUT methods -> Replace existing data.

    @PutMapping
    public @ResponseBody ResponseEntity<?> update(@RequestBody Bouwbedrijf bouwbedrijf){
        Integer id = bouwbedrijf.getId();
        Bouwbedrijf storedBouwbedrijf = bouwbedrijfRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bouwbedrijfid","id", id));


        if (bouwbedrijf.getKvk() != null){
            storedBouwbedrijf.withKvk(bouwbedrijf.getKvk());
        }
        if (bouwbedrijf.getNaam() != null){
            storedBouwbedrijf.withNaam(bouwbedrijf.getNaam());
        }

        bouwbedrijfRepository.save(storedBouwbedrijf);
        return ResponseEntity.ok(String.format("Successfully updated Bouwbedrijf with ID: %s.", id));
    }


    //DELETE methods -> Remove data.

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<?> delete(
            @PathVariable Integer id) {

        Bouwbedrijf bouwbedrijf = bouwbedrijfRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Bouwbedrijfid","id", id));
        bouwbedrijfRepository.delete(bouwbedrijf);
        return ResponseEntity.ok("Succesfully deleted from database.");

    }


}