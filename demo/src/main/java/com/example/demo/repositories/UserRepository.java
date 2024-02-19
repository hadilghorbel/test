package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.entities.User;

import jakarta.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);

    @Query("select u FROM User u WHERE u.manager.id = :id")
    List<User> findAllByManagerID(Long id);

    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name = 'ROLE_MANAGER'")
    List<User> findOnlyManagers();

    @Query("select u.id FROM User as u WHERE u.email = :Email")
    Long findIDByEmail(@Param("Email") String Email);

    Optional<User> findByEmailIgnoreCase(String email);

    @Query("select u from User as u where u.id =:id")
    User findByyId(Long id);

    @Query("select u.manager.id FROM User u where u.id =:userID")
    Long findManagerById(Long userID);

    Optional<User> findByFirstNameIgnoreCase(String firstName);

    Optional<User> findByLastNameIgnoreCase(String lastName);

    @Query("select u.actif FROM User as u where u.email =:email")
    boolean isActif(@Param("email") String email);

    Boolean existsByFirstName(String firstName);

    Boolean existsByEmail(String email);

    @Transactional
    @Modifying
    @Query("update User u set u.lastLoggedIn =:time  where u.id =:id ")
    int updateUserTimeLoggedIn( @Param("id") Long id, @Param("time") Date time);

    @Transactional
    @Modifying
    @Query("update User u set u.lastLoggedOut =:time  where u.id =:id ")
    int updateUserTimeLoggedOut( @Param("id") Long id, @Param("time") Date time);

    @Query("SELECT COUNT(*) FROM User ")
    int numberOfUsers();
}
