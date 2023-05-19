package ru.stomprf.discount51.repo;

import org.springframework.data.repository.CrudRepository;
import ru.stomprf.discount51.domain.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByPhoneNumber(String phoneNumber);
    User findById(int id);
}
