package ru.stomprf.discount51;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    //ChatGPT generation
    public static boolean validateRussianPhoneNumber(String phoneNumber) {
        // Regular expression pattern for a valid Russian phone number without spaces and brackets
        String regex = "^\\+7\\d{10}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.matches();
    }

    public static void main(String[] args) {
        System.out.println(validateRussianPhoneNumber("+79216059950"));
    }
}
