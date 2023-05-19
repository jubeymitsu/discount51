package ru.stomprf.discount51.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.stomprf.discount51.repo.UserRepository;

@RestController
public class TestController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/test")
    public String hello(@RequestParam("phone") String phoneNumber){
        System.out.println(phoneNumber);
        return userRepository.findByPhoneNumber(phoneNumber).toString();
    }

}
