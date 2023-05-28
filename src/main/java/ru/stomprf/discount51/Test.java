package ru.stomprf.discount51;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

import static java.lang.System.out;

public class Test {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, URISyntaxException, ParseException {

        SmsSender sms = new SmsSender("discount51", "053679eb56275da89534a4da512a3d73", false);

        JSONObject resultJson = sms.MessageSend("Я тебя люблю <3", "79211555579", "sendertest");
        if (resultJson.get("status").equals("success")) {
            out.println("Сообщение успешно отправленно, стоимость отправки: " + resultJson.get("price") + " рублей");
        } else {
            out.println("Произошла ошибка: " + resultJson.get("message"));
        }

//        out.println("TEEEEEEST");
//        JSONObject resultJson = sms.MessageBalance();
//        out.println("TEEEEEEST");
//        if (resultJson.get("status").equals("success"))
//        {
//            out.println("Баланс: "+resultJson.get("balance") + " рублей");
//        }
//        else
//        {
//            out.println("Произошла ошибка: "+resultJson.get("message"));
//        }
    }

}
