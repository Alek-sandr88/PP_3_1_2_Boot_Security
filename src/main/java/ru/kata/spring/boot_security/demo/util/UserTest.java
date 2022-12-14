package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.servise.RoleServise;
import ru.kata.spring.boot_security.demo.servise.UserServise;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserTest implements ApplicationRunner {

    private UserServise userServise;
    private RoleServise roleServise;

    @Autowired
    public void setRoleServise(RoleServise roleServise) {
        this.roleServise = roleServise;
    }

    @Autowired
    public void setUserServise(UserServise userServise) {
        this.userServise = userServise;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");

        Set<Role> rolAdmin = new HashSet<>();
        Set<Role> rolUser = new HashSet<>();

        rolAdmin.add(roleAdmin);
        rolUser.add(roleUser);

        User userRolAdmin = new User("admin", "admin",
                "admin", "admin", rolAdmin);

        User userRolUser = new User("user", "user",
                "user", "user", rolUser);

        roleServise.addRole(roleAdmin);
        roleServise.addRole(roleUser);
        userServise.addUser(userRolAdmin);
        userServise.addUser(userRolUser);

    }
}
