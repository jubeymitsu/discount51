package ru.stomprf.discount51;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.stomprf.discount51.service.SmsService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SmsSender implements SmsService {

    @Value("${mainsms.project}")
    private String _project;

    @Value("${mainsms.apiKey}")
    private String _apiKey;

    @Value("${mainsms.isTest}")
    private Boolean _isTest;

    @Value("${mainsms.baseDomain}")
    private String _baseDomain;

    @Deprecated
    public void options(){
        System.out.println("SMS SENDER OPTIONS");
        System.out.println("Project: " + _project);
        System.out.println("ApiKey: " + _apiKey);
        System.out.println("Is test: " + _isTest);
        System.out.println("Base domain: " + _baseDomain);
    }

    public SmsSender(String projectName, String apiKey, Boolean isTest) {
        _project = projectName;
        _apiKey = apiKey;
        _isTest = isTest;
    }


    public SmsSender(String projectName, String apiKey) {
        _project = projectName;
        _apiKey = apiKey;
        _isTest = false;
    }

    public SmsSender(){
    }

    public JSONObject sendMessage(String message, String recipients, String sender) throws IOException, NoSuchAlgorithmException, URISyntaxException, ParseException {
        Map<String, String> data = new HashMap<>();
        data.put("test" , _isTest==true ? "1" : "0");
        data.put("project", _project);
        data.put("recipients", recipients);
        data.put("sender", sender);
        data.put("message", message);
        String sign = GetSign(data);
        data.put("sign", sign);
        String result =  SendPost(_baseDomain + "message/send/", data);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(result);
        JSONObject jsonObj = (JSONObject) obj;
        return jsonObj;
    }


    public JSONObject MessageStatus(String messages_id) throws IOException, NoSuchAlgorithmException, URISyntaxException, ParseException {
        Map<String, String> data = new HashMap<String, String>();
        data.put("project", _project);
        data.put("messages_id", messages_id);
        String sign = GetSign(data);
        data.put("sign", sign);
        String result =  SendPost(_baseDomain + "message/status/", data);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(result);
        JSONObject jsonObj = (JSONObject) obj;
        return jsonObj;
    }



    public JSONObject MessagePrice(String message, String recipients, String sender) throws IOException, NoSuchAlgorithmException, URISyntaxException, ParseException {
        Map<String, String> data = new HashMap<String, String>();
        data.put("project", _project);
        data.put("recipients", recipients);
        data.put("sender", sender);
        data.put("message", message);
        String sign = GetSign(data);
        data.put("sign", sign);
        String result =  SendPost(_baseDomain + "message/price/", data);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(result);
        JSONObject jsonObj = (JSONObject) obj;
        return jsonObj;
    }


    public JSONObject MessageBalance() throws IOException, NoSuchAlgorithmException, URISyntaxException, ParseException {
        Map<String, String> data = new HashMap<String, String>();
        data.put("project", _project);
        String sign = GetSign(data);
        data.put("sign", sign);
        String result =  SendPost(_baseDomain + "message/balance/", data);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(result);
        JSONObject jsonObj = (JSONObject) obj;
        return jsonObj;
    }


    private String GetSign(Map<String, String> data) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String step1 = "";
        for (Map.Entry<String, String> entry : data.entrySet())
        {
            step1 +=entry.getValue();
        }
        step1 += _apiKey;
        String step2 = SHA1(step1);
        String sign = MD5(step2);
        return sign;
    }

    private String MD5(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.reset();
        m.update(text.getBytes("UTF-8"));
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1, digest);
        String hashtext = bigInt.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext;
    }

    private String SHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(text.getBytes("UTF-8"));
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    private String SendPost(String link, Map<String, String> data) throws IOException, URISyntaxException {

        // Create a new HttpClient and Post Header
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(link);
        post.setHeader("Content-Type",
                "application/x-www-form-urlencoded;charset=UTF-8");
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(data.size());
            for (Map.Entry<String, String> entry : data.entrySet())
            {
                System.out.println(entry.getKey() + "/" + entry.getValue());
                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            HttpResponse responsePOST = client.execute(post);
            HttpEntity resEntity = responsePOST.getEntity();
            if (resEntity != null)
            {
                String result = EntityUtils.toString(resEntity);
                return result;
            }

            return "";
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
        return null;

    }


}
