package com.example.springproj8;

import com.nimbusds.srp6.*;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SRPTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    public static String username = "yaqub0v";
    public static String password = "youcannotpredictthispassword";
    public static BigInteger s = new BigInteger("1031322761846613224834536");
    public static BigInteger v = new BigInteger("2510174848470831614633167459448252804347764498751046077923431241562915250457436529316888461164208599573927059863638499424874945406214587637961689657442699");

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Test
    public void verifierGenerator() {
        SRP6CryptoParams config = SRP6CryptoParams.getInstance();
        SRP6VerifierGenerator gen = new SRP6VerifierGenerator(config);
        BigInteger salt = new BigInteger("da641e73455174c1c5e8", 16);
        BigInteger verifier = gen.generateVerifier(salt, username, password);
        JSONObject response = new JSONObject();
        response.put("v", verifier);
        response.put("s", salt);
        System.out.println(response);
    }

    @Test
    public void srpTest() {
        SRP6ClientSession client = new SRP6ClientSession();
        client.step1(username, password);

        HttpEntity<String> step1Entity = new HttpEntity<>(username, headers);
        String response = restTemplate.exchange(
                createURLWithPort("/step1"), HttpMethod.POST, step1Entity, String.class).getBody();
        assert response != null;
        BigInteger B = new BigInteger(response);

        SRP6CryptoParams config = SRP6CryptoParams.getInstance();

        SRP6ClientCredentials cred = null;

        try {
            cred = client.step2(config, s, B);
        } catch (SRP6Exception e) {
            fail();
        }
        assert cred != null;
        Step2Body step2Body = new Step2Body(cred.A, cred.M1);
        HttpEntity<Step2Body> step2Entity = new HttpEntity<>(step2Body, headers);

        String M2 = restTemplate.postForEntity(
                createURLWithPort("/step2"), step2Entity, String.class).getBody();
        assertNotEquals(M2, "");
    }
}