package com.example.springproj4;
import org.json.JSONException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class Springproj4ApplicationTests {

    @Test
    public void check_title() throws JSONException {
        String link = "https://api.nytimes.com/svc/books/v3/lists.json?list=Combined Print and E-Book Nonfiction&api-key=GRApLbPdvoGHc7QpCTGSAVSMURWA1mY8";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> out = restTemplate.exchange(link, HttpMethod.GET, entity, String.class);
        JSONObject jsonObject = new JSONObject(out.getBody());
        JSONArray jsonArray = jsonObject.getJSONArray("results");
        boolean non_empty = true;
        for (int n = 0; n < jsonArray.length(); n++){
            String t = (String) jsonArray.getJSONObject(n).getJSONArray("book_details").getJSONObject(0).get("title");
            if (t.length() == 0){
                non_empty = false;
            }
        }
        assertTrue(non_empty);
    }

    @Test
    public void check_rank() throws JSONException {
        String link = "https://api.nytimes.com/svc/books/v3/lists.json?list=Combined Print and E-Book Nonfiction&api-key=GRApLbPdvoGHc7QpCTGSAVSMURWA1mY8";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> out = restTemplate.exchange(link, HttpMethod.GET, entity, String.class);
        JSONObject jsonObject = new JSONObject(out.getBody());
        JSONArray jsonArray = jsonObject.getJSONArray("results");
        boolean complete = true;
        for (int n = 0; n < jsonArray.length(); n++){
            Integer r = (Integer) jsonArray.getJSONObject(n).get("rank");
            if (r != n + 1){
                complete = false;
            }
        }
        assertTrue(complete);
    }

}