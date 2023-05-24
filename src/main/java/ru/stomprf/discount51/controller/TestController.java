package ru.stomprf.discount51.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.stomprf.discount51.domain.User;
import ru.stomprf.discount51.repo.UserRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    final
    UserRepository userRepository;

    public TestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/phoneSearch")
    public List<User> hello(@RequestParam(name = "phoneNumber") String phoneNumber){
        System.out.println(phoneNumber);
        return userRepository.findByPhoneNumberLike("%" + phoneNumber + "%");
    }
}
