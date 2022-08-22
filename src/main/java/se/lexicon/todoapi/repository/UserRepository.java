package se.lexicon.todoapi.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import se.lexicon.todoapi.model.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {

    //select * from user u where u.username = :username
    //@Query("select u from User u where u.username = :un")
    Optional<User> findByUsername(@Param("un") String username);

    // select count(*) from user u where u.username = :username
    boolean existsByUsername(String username);

    @Modifying
    @Query("update User u set u.expired = :status where u.username = :username")
    void updateExpiredByUsername(@Param("username") String username, @Param("status") boolean status);

    @Modifying
    @Query("update User u set u.password = :pwd where u.username = :username")
    void updatePasswordByUsername(@Param("username") String username, @Param("pwd") String newPassword);

}
