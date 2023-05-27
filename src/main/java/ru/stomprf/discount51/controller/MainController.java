package ru.stomprf.discount51.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.stomprf.discount51.domain.User;
import ru.stomprf.discount51.repo.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private final UserRepository userRepository;

    @Autowired
    public MainController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String mainPage(@RequestParam(required = false, name = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "main";
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);

        model.addAttribute("users", users);

        return "users";
    }

    //Create new user
    @GetMapping("/users/new")
    public String createUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user-form";
    }

    @PostMapping("/users/save")
    public String saveUser(@ModelAttribute User user, Model model) {
        System.out.println("Inside method");
        System.out.println(user);
        User savedUser = userRepository.save(user);
        System.out.println("Save user: " + savedUser);

        return "redirect:/users";

    }

    @GetMapping("/users/{id}")
    public String editUser(@PathVariable Integer id, Model model) {
        try {
            User user = userRepository.findById(id).get();

            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit user (ID: " + id + ")");

            return "user-form";
        } catch (Exception e) {
            System.out.println("ERROR INSIDE EDITUSER()");
            return "redirect:/users";
        }
    }

    @GetMapping("/users/page/{id}")
    public String userPage(@PathVariable Integer id, Model model) {
        User user = userRepository.findById(id).get();

        model.addAttribute("user", user);
        return "user-page";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
        System.out.println("User by id: " + id + "was deleted.");
        return "redirect:/users";
    }

    @GetMapping("/phoneSearch")
    public String phoneSearch() {
        return "phoneSearch";
    }
}
