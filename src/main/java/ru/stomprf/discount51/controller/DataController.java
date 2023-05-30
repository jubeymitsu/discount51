package ru.stomprf.discount51.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.stomprf.discount51.domain.Status;
import ru.stomprf.discount51.domain.VerificationCode;
import ru.stomprf.discount51.service.UserService;

import java.util.concurrent.TimeUnit;

@RestController
public class DataController {

    @Autowired
    private final UserService userService;

    public DataController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/verify")
    public Status verify(@RequestBody VerificationCode verificationCode) throws InterruptedException {
        System.out.println("VERIFICATION CODE HAS BEEN RECEIVED: " + verificationCode);
        Status status;
        if (userService.verify(verificationCode)) {
            status = new Status("success");
            return status;
        }
        status = new Status("failure");
        return status;
    }
}
