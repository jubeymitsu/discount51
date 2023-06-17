package ru.stomprf.discount51.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.stomprf.discount51.domain.User;
import ru.stomprf.discount51.repo.UserRepository;

import java.util.stream.StreamSupport;

@Controller
public class MainController {

    private final UserRepository userRepository;

    public MainController(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        Iterable<User> all = userRepository.findAll();
        long count = StreamSupport.stream(all.spliterator(), false).count();
        model.addAttribute("count", count);
        return "main";
    }

    @GetMapping("/phoneSearch")
    public String phoneSearch() {
        return "phoneSearch";
    }
}
