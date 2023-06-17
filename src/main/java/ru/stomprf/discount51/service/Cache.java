package ru.stomprf.discount51.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class Cache {

    @Bean
    public HashMap<Integer, String> verificationCodeCache(){
        return new HashMap<>();
    }


}
