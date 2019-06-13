package nl.highway.iungomain.Controllers;


import nl.highway.iungomain.Datamodel.Bouwbedrijf.Bouwbedrijf;
import nl.highway.iungomain.Datamodel.Bouwbedrijf.BouwbedrijfRepository;
import nl.highway.iungomain.Datamodel.Project.Project;
import nl.highway.iungomain.Datamodel.Project.ProjectRepository;
import nl.highway.iungomain.Exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(path="/iungo/project")
//TODO: Define access level when Oauth2 is operational.
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BouwbedrijfRepository bouwbedrijfRepository;

    // GET methods -> Retrieve information.


    // POST methods -> Add new data.

    @PostMapping(path="/")
    public @ResponseBody
    ResponseEntity<?> add(
            @RequestParam Integer bouwbedrijfid,
            @RequestParam String naam,
            @RequestParam String locatie,
            @RequestParam Date startdatum,
            @RequestParam (required = false) Date einddatum){


        Bouwbedrijf b = bouwbedrijfRepository.findById(bouwbedrijfid).orElseThrow(()-> new ResourceNotFoundException("Bouwbedrijfid","id", bouwbedrijfid));

        Project n = new Project()
                .withBouwbedrijf(b)
                .withNaam(naam)
                .withLocatie(locatie)
                .withStartDatum(startdatum);

        if (einddatum != null){
            n.withEindDatum(einddatum);
        }
        projectRepository.save(n);

        return ResponseEntity.ok("Added new project to database.");
    }

    // PUT methods -> Replace existing data.


    //DELETE methods -> Remove data.



}