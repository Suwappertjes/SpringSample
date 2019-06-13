package nl.highway.iungomain.Datamodel.Aanvraag;

import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface AanvraagRepository extends CrudRepository<Aanvraag, Integer> {


    // Maybe only findbyID is available here

}