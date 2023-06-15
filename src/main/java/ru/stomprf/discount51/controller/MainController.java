package ru.stomprf.discount51.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.stomprf.discount51.domain.User;
import ru.stomprf.discount51.repo.UserRepository;
import ru.stomprf.discount51.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Controller
public class MainController {

    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public MainController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/")
    public String mainPage(@RequestParam(required = false, name = "name") String name, Model model) {
        model.addAttribute("name", name);
        Iterable<User> all = userRepository.findAll();
        long count = StreamSupport.stream(all.spliterator(), false).count();
        model.addAttribute("count", count);
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
    @GetMapping("/users/create")
    public String createUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("pageTitle", "Новый пользователь");
        return "user-form";
    }

    @PostMapping("/users/save")
    public String saveUser(@ModelAttribute User user, Model model) {
        try {
            if (user.getId() == null) {
                User savedUser = userService.saveUser(user);
                return String.format("redirect:/verification/page/%d", savedUser.getId());
            }
            userService.saveUser(user);
        } catch (Exception e) {
            System.out.println("User not added");
            e.printStackTrace();
        }
        return "redirect:/users";
    }

    @GetMapping("/users/{id}")
    public String editUser(@PathVariable Integer id, Model model) {
        try {
            User user = userRepository.findById(id).get();

            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Изменить (ID: " + id + ")");

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
