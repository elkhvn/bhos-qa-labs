package com.example.springproj2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import javax.net.ssl.HttpsURLConnection;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Springproj2ApplicationTests {

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    public String url = "https://60a21d3f745cd70017576092.mockapi.io/api/v1/repos";

    @Test
    @DisplayName("Integration Test to check SSL certificate")
    void certValidation() throws CertificateException, IOException {
        URL url = new URL(this.url);

        URLConnection con = url.openConnection();
        HttpsURLConnection securecon = (HttpsURLConnection) con;
        securecon.connect();

        Certificate[] certs = securecon.getServerCertificates();

        FileInputStream fis = new FileInputStream("cert.cer");

        CertificateFactory fac = CertificateFactory.getInstance("X509");
        X509Certificate cert = (X509Certificate) fac.generateCertificate(fis);

        assertEquals(cert, certs[0]);
    }


    @Test
    @DisplayName("Integration test of 'https://60a21d3f745cd70017576092.mockapi.io/api/v1/repos' link")
    public void firstURLTest() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        JSONArray objects = new JSONArray(response.getBody());
        HashMap<String, String> map = new HashMap<>();

        for (int i = 0; i < objects.length(); i++) {
            JSONObject o = (JSONObject) objects.get(i);
            map.put(o.getString("id"), o.getString("name"));

        }
        //System.out.println(map.get("1"));
        assertTrue(map.get("1") != map.get("2"));

    }

}
