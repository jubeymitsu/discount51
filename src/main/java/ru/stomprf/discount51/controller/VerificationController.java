package ru.stomprf.discount51.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.stomprf.discount51.domain.Status;
import ru.stomprf.discount51.domain.User;
import ru.stomprf.discount51.domain.VerificationCode;
import ru.stomprf.discount51.service.UserService;

@Controller
@RequestMapping("/verification")
public class VerificationController {

    @Autowired
    private final UserService userService;

    public VerificationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/page/{id}")
    public String verificationPage(@PathVariable Integer id, Model model) {
        User user = userService.getUser(id);
        if (user == null)
            return "redirect:/users";

        if (userService.sendVerificationCode(user)) {
            model.addAttribute("user", user);
            return "verification-page";
        }
        return "redirect:/users";
    }

    @PostMapping("/verify")
    public @ResponseBody Status verify(@RequestBody VerificationCode verificationCode) {
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
