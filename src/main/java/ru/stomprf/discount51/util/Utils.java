package ru.stomprf.discount51.util;

import ru.stomprf.discount51.exception.InvalidPhoneNumberException;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private static final Random RANDOM = new Random();

    //ChatGPT generation
    public static void validateRussianPhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {
        // Regular expression pattern for a valid Russian phone number without spaces and brackets
        String regex = "^\\+7\\d{10}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (!matcher.matches())
            throw new InvalidPhoneNumberException(phoneNumber);
    }

    public static String generateCode() {
        int code = RANDOM.nextInt(10000);

        return String.format("%04d", code);
    }

    public static void main(String[] args) {
        validateRussianPhoneNumber("'+792160599501' doesn't match pattern");
    }
}