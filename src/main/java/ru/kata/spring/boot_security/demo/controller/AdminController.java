package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.servise.RoleServise;
import ru.kata.spring.boot_security.demo.servise.UserServise;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
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

    @GetMapping()
    public String allUsers(Model model) {
        List<User> users = userServise.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("user.roles", users);
        return "admin";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userServise.getUserById(id));
        return "admin";
    }

    @GetMapping("/create")
    public String newUserForm(Model model, @ModelAttribute("user") User user) {
        List<Role> listRoles = roleServise.getAllRoles();
        model.addAttribute("listRoles", listRoles);
        return "create";
    }

    @PostMapping("/create")
    public String create(User user) {
        userServise.addUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userServise.removeUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/update/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userServise.getUserById(id));
        model.addAttribute("listRoles", roleServise.getAllRoles());
        return "update";
    }

    @PatchMapping("/update")
    public String editUsers(@RequestParam("listRoles") ArrayList<Long> roles, User user) {
        user.setRoles(roleServise.findByIdRoles(roles));
        userServise.updateUser(user);
        return "redirect:/admin";
    }
}
