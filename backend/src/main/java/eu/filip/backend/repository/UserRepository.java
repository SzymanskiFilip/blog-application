package eu.filip.backend.repository;

import eu.filip.backend.dto.RegisterDataDto;
import eu.filip.backend.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
    User findByUsername(String username);
    User findByEmail(String email);
}
