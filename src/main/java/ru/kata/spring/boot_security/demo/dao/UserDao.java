package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    User getUserById(long id);

    void addUser(User user);

    void removeUser(long id);

    void updateUser(User user);

    User findByUsername(String username);
}
