package com.example.springproj1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Springproj1ApplicationTests {

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();


    @Test
    @DisplayName("Integration test of 'https://60a21d3f745cd70017576092.mockapi.io/api/v1/repos' link")
    public void firstURLTest() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange("https://60a21d3f745cd70017576092.mockapi.io/api/v1/repos", HttpMethod.GET, entity, String.class);
        assertEquals( MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
    }

    
    @Test
    @DisplayName("Integration test of 'https://60a21d3f745cd70017576092.mockapi.io/api/v1/repos/1/branches' link")
    public void secondURLTest() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange("https://60a21d3f745cd70017576092.mockapi.io/api/v1/repos/1/branches", HttpMethod.GET, entity, String.class);
        assertEquals( MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
    }

    @Test
    @DisplayName("Integration test of 'https://60a21d3f745cd70017576092.mockapi.io/api/v1/repos/1/branches/1/commits' link")
    public void thirdURLTest() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange("https://60a21d3f745cd70017576092.mockapi.io/api/v1/repos/1/branches/1/commits", HttpMethod.GET, entity, String.class);
        assertEquals( MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
    }
}


