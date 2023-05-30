package ru.stomprf.discount51.service;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import ru.stomprf.discount51.SmsSender;
import ru.stomprf.discount51.Utils;
import ru.stomprf.discount51.domain.User;
import ru.stomprf.discount51.domain.VerificationCode;
import ru.stomprf.discount51.repo.UserRepository;

import java.util.HashMap;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final SmsSender smsSender;
    private final HashMap<Integer, String> verificationCodeCache;

    public UserService(UserRepository userRepository, SmsSender smsSender, HashMap<Integer, String> verificationCodeCache) {
        this.userRepository = userRepository;
        this.smsSender = smsSender;
        this.verificationCodeCache = verificationCodeCache;
        this.smsSender.options();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUser(Integer id) {
        return userRepository.findById(id).get();
    }

    public boolean sendVerificationCode(User user) {
        String recipientPhone = user.getPhoneNumber().replace("+", "");
        String code = Utils.generateCode();
        System.out.println("---GENERATED CODE---");
        System.out.println(code + '\n');

        verificationCodeCache.put(user.getId(), code);

        try {
            JSONObject jsonObject = smsSender.MessageSend(code, recipientPhone, "sendertest");
            System.out.println("SMS SEND RESULT: " + jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean verify(VerificationCode verificationCode) {
        Integer id = verificationCode.id();
        String code = verificationCode.code();
        if (verificationCodeCache.get(id) == null)
            return false;
        if (verificationCodeCache.get(id).equals(code)) {
            User user = userRepository.findById(id).get();
            user.setVerified(true);
            userRepository.save(user);
            verificationCodeCache.remove(id);
            return true;
        }

        return false;
    }
}
