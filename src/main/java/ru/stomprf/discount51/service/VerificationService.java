package ru.stomprf.discount51.service;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stomprf.discount51.domain.User;
import ru.stomprf.discount51.domain.VerificationCode;
import ru.stomprf.discount51.exception.VerificationException;
import ru.stomprf.discount51.util.Utils;

import java.util.HashMap;

@Service
public class VerificationService {

    private final HashMap<Integer, String> verificationCodeCache;
    private final SmsService smsService;
    private final UserService userService;

    public VerificationService(@Autowired HashMap<Integer, String> verificationCodeCache,
                               @Autowired SmsService smsService,
                               @Autowired UserService userService) {
        this.verificationCodeCache = verificationCodeCache;
        this.smsService = smsService;
        this.userService = userService;
    }

    public void sendVerificationCode(Integer id) {
        User user = userService.findById(id);
        if (user.isVerified())
            throw new VerificationException("User already verified!");

        String recipientPhone = user.getPhoneNumber().replace("+", "");
        String code = Utils.generateCode();
        System.out.println("---GENERATED CODE---");
        System.out.println(code + '\n');

        verificationCodeCache.put(user.getId(), code);

        try {
            JSONObject jsonObject = smsService.sendMessage(code, recipientPhone, "sendertest");
            System.out.println("SMS SEND RESULT: " + jsonObject);
        } catch (Exception e) {
            throw new RuntimeException("Message send failure: " + e.getMessage());
        }
    }

    public boolean verify(VerificationCode verificationCode) {
        Integer id = verificationCode.id();
        String code = verificationCode.code();
        if (verificationCodeCache.get(id) == null)
            return false;
        if (verificationCodeCache.get(id).equals(code)) {
            User user = userService.findById(id);
            user.setVerified(true);
            userService.update(user);
            verificationCodeCache.remove(id);
            return true;
        }
        return false;
    }
}
