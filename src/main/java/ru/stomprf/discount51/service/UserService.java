package ru.stomprf.discount51.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stomprf.discount51.domain.User;
import ru.stomprf.discount51.exception.UserNotFoundException;
import ru.stomprf.discount51.repo.UserRepository;
import ru.stomprf.discount51.util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        Utils.validateRussianPhoneNumber(user.getPhoneNumber());
        user.setVerified(false);
        return userRepository.save(user);
    }

    public User update(User user) {
        Utils.validateRussianPhoneNumber(user.getPhoneNumber());
        if (user.getId() == null)
            throw new UserNotFoundException("User id is null");
        return userRepository.save(user);
    }
    public User findById(Integer id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent())
            return optional.get();
        throw new UserNotFoundException("User with id: " + id + "not present");
    }
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }


}
