package codegym.repository;

import codegym.model.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IAppUserRepository extends CrudRepository<AppUser, Long> {
    Optional<AppUser> findByName(String name);

}
