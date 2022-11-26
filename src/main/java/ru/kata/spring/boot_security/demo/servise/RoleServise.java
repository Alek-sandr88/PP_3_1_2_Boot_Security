package ru.kata.spring.boot_security.demo.servise;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleServise {

    List<Role> getAllRoles();

    Role findById(Long id);

    void saveRole(Role role);
}
