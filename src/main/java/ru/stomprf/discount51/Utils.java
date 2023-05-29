package ru.stomprf.discount51;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private static final Random RANDOM = new Random();

    //ChatGPT generation
    public static boolean validateRussianPhoneNumber(String phoneNumber) {
        // Regular expression pattern for a valid Russian phone number without spaces and brackets
        String regex = "^\\+7\\d{10}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.matches();
    }

    public static String generateCode() {
        int code = RANDOM.nextInt(10000);

        return String.format("%04d", code);
    }

    public static void main(String[] args) {
//        System.out.println(validateRussianPhoneNumber("+79216059950"));
//        for (int i = 0; i < 25; i++) {
//            System.out.println(generateCode());
    }
}