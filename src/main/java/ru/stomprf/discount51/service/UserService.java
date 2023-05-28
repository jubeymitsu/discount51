package ru.stomprf.discount51.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import ru.stomprf.discount51.SmsSender;
import ru.stomprf.discount51.domain.User;
import ru.stomprf.discount51.repo.UserRepository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

import static java.lang.System.out;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final SmsSender smsSender;

    public UserService(UserRepository userRepository, SmsSender smsSender) {
        this.userRepository = userRepository;
        this.smsSender = smsSender;
    }

    public User saveUser(User user) {
        User savedUser = null;
        if (user.isVerified()){
            savedUser = userRepository.save(user);
            System.out.println("Save user: " + savedUser);
        }
        else {
            verificateUser(user);
        }
        return savedUser;
    }

    private void verificateUser(User user) {
        String phoneNumber = user.getPhoneNumber().replace("+", "");
        System.out.println("User phone number: " + phoneNumber);

        try {
            JSONObject resultJson = smsSender.MessageSend("12345", phoneNumber, "testsender");
            out.println("Сообщение успешно отправленно: " + resultJson);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
