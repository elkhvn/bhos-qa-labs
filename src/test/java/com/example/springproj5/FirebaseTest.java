package com.example.springproj5;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;

public class FirebaseTest {

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    public String authenticate() throws AuthenticationException, NullPointerException {
        JSONObject data = new JSONObject();
        data.put("email", System.getenv("FIREBASE_EMAIL"));
        data.put("password", System.getenv("FIREBASE_PASSWORD"));
        data.put("returnSecureToken", true);
        HttpEntity<String> entity = new HttpEntity<>(data.toString(), headers);

        ResponseEntity<String> response = restTemplate.exchange(
                ApiUrlConstants.FIREBASE_SIGNIN_EMAIL_AND_PASSWORD.concat(System.getenv("FIREBASE_API_KEY")), HttpMethod.POST, entity, String.class);
        return response.getBody();
    }

    public String getUserDetails(String idToken, String userId) {
        headers.put("Authorization", Collections.singletonList("Bearer ".concat(idToken)));
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                ApiUrlConstants.FIRESTORE_USERS.concat(userId), HttpMethod.GET, entity, String.class);
        return response.getBody();
    }

    @Test
    public void endToEndTest() throws JSONException {
        String responseBody = authenticate();
        JSONObject resBody = new JSONObject(responseBody);
        String idToken = (String) resBody.get("idToken");
        String userId = (String) resBody.get("localId");
        String userDetails = new JSONObject(getUserDetails(idToken, userId)).get("fields").toString();
        try {
            User user = new ObjectMapper().readValue(userDetails, User.class);
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }
}