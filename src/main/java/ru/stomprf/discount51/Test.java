package ru.stomprf.discount51;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

import static java.lang.System.out;

@Component
public class Test {

    private static String apiKey = "";
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, URISyntaxException, ParseException {
        out.println("API KEY: " + apiKey);
        SmsSender sms = new SmsSender("discount51", apiKey, true);

        JSONObject resultJson = sms.sendMessage("", "", "sendertest");
        if (resultJson.get("status").equals("success")) {
            out.println("Сообщение успешно отправленно, стоимость отправки: " + resultJson.get("price") + " рублей");
        } else {
            out.println("Произошла ошибка: " + resultJson.get("message"));
        }
    }

}
