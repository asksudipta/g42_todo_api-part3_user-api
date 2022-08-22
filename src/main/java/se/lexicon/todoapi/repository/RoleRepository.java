package se.lexicon.todoapi.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.todoapi.model.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    List<Role> findAll();

    Optional<Role> findByName(String name);
}
