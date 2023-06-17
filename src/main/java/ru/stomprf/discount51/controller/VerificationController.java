package ru.stomprf.discount51.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.stomprf.discount51.domain.Status;
import ru.stomprf.discount51.domain.User;
import ru.stomprf.discount51.domain.VerificationCode;
import ru.stomprf.discount51.service.VerificationService;

@Controller
@RequestMapping("/verification")
public class VerificationController {

    private final VerificationService verificationService;

    public VerificationController(@Autowired VerificationService verificationService) {
        this.verificationService = verificationService;
    }


    @GetMapping("/page/{id}")
    public String verificationPage(@PathVariable Integer id, Model model) {
        try {
            verificationService.sendVerificationCode(id);
            model.addAttribute("id", id);
            return "verification-page";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/users";
        }
    }

    @PostMapping("/verify")
    public @ResponseBody Status verify(@RequestBody VerificationCode verificationCode) {
        System.out.println("VERIFICATION CODE HAS BEEN RECEIVED: " + verificationCode);
        Status status;
        if (verificationService.verify(verificationCode)) {
            status = new Status("success");
            return status;
        }
        status = new Status("failure");
        return status;
    }
}
