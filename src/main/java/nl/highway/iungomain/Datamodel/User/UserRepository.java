package nl.highway.iungomain.Datamodel.User;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);
}