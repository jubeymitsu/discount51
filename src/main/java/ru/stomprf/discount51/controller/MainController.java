package ru.stomprf.discount51.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String mainPage(@RequestParam(required = false, name = "name") String name, Model model){

        model.addAttribute("name", name);
        return "main";

    }

    @GetMapping("/users")
    public String showUsers(Model model){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);

        model.addAttribute("users", users);

        return "users";

    }



}
