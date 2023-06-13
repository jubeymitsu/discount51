package ru.stomprf.discount51.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

public interface SmsService {

    public JSONObject sendMessage(String message, String recipients, String sender) throws IOException, NoSuchAlgorithmException, URISyntaxException, ParseException;

}
