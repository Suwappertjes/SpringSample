package nl.highway.iungomain.Datamodel.Bouwbedrijf;

import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BouwbedrijfRepository extends CrudRepository<Bouwbedrijf, Integer> {

    Optional<Bouwbedrijf> findByKvk(Integer kvk);
}