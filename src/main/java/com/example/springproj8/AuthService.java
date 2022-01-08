package com.example.springproj8;

import com.nimbusds.srp6.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();

    public static SRP6CryptoParams config;
    public static SRP6ServerSession serverSession;

    private static final Map<String, Map> database = new HashMap<>() {
        {
            put("yaqub0v", new HashMap<String, BigInteger>() {{
                put("salt", new BigInteger("1031322761846613224834536"));
                put("verifier", new BigInteger("2510174848470831614633167459448252804347764498751046077923431241562915250457436529316888461164208599573927059863638499424874945406214587637961689657442699"));
            }});
        }
    };

    public static String username = "yaqub0v";
    public static String password = "youcannotpredictthispassword";
    public static BigInteger s = new BigInteger("1031322761846613224834536");
    public static BigInteger v = new BigInteger("2510174848470831614633167459448252804347764498751046077923431241562915250457436529316888461164208599573927059863638499424874945406214587637961689657442699");


    public String step1(String username) {
        if (!database.containsKey(username)) {
            return "";
        }
        Map user = database.get(username);
        config = SRP6CryptoParams.getInstance();
        serverSession = new SRP6ServerSession(config);
        return serverSession.step1(username, (BigInteger) user.get("salt"), (BigInteger) user.get("verifier")).toString();
    }

    public String step2(BigInteger A, BigInteger M1) throws SRP6Exception {
        try{
            return serverSession.step2(A, M1).toString();
        } catch (SRP6Exception e) {
            return "";
        }
    }

    public String simulation() {
        SRP6ClientSession client = new SRP6ClientSession();
        client.step1(username, password);

        HttpEntity<String> step1Entity = new HttpEntity<>(username, headers);
        String response = restTemplate.exchange(
                "http://localhost:8080/step1", HttpMethod.POST, step1Entity, String.class).getBody();
        assert response != null;
        BigInteger B = new BigInteger(response);

        SRP6CryptoParams config = SRP6CryptoParams.getInstance();

        SRP6ClientCredentials cred = null;

        try {
            cred = client.step2(config, s, B);
        } catch (SRP6Exception e) {
            return "";
        }
        assert cred != null;
        Step2Body step2Body = new Step2Body(cred.A, cred.M1);
        HttpEntity<Step2Body> step2Entity = new HttpEntity<>(step2Body, headers);

        return restTemplate.postForEntity(
                "http://localhost:8080/step2", step2Entity, String.class).getBody();
    }
}