package com.example.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entities.User;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface UserService {
    User addUser(User userToAdd);

    List<User> retrieveAllUsers();

    User saveOrUpdate(User user);

    Optional<User> findById(Long id);

    void deleteById(Long id);

    List<User> getEmployees(Long id);

    User updateUserById(User user, Long id);
    User deleteEmployeeByID(Long id);
    List<User> findOnlyManagers();
    boolean checkPassword(User user,String password);
    void changePassword(User user, String newPassword);

}
