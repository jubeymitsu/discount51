package ru.stomprf.discount51;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

import static java.lang.System.out;

@SpringBootTest
class Discount51ApplicationTests {

    @Test
    void contextLoads() throws IOException, NoSuchAlgorithmException, URISyntaxException, ParseException {

        SmsSender sms = new SmsSender("discount51","053679eb56275da89534a4da512a3d73",true);

        JSONObject resultJson = sms.MessageSend("I love you!", "79211555579", "Ilya");
        if (resultJson.get("status").equals("success"))
        {
            out.println("Сообщение успешно отправленно, стоимость отправки: "+resultJson.get("price")+" рублей");
        }
        else
        {
            out.println("Произошла ошибка: "+resultJson.get("message"));
        }
    }

}
