package com.example.springproj4;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@RestController
public class MainController{

    @GetMapping("/")
    public String title() {
        String url = "https://api.nytimes.com/svc/books/v3/lists.json?list=Combined Print and E-Book Nonfiction&api-key=GRApLbPdvoGHc7QpCTGSAVSMURWA1mY8";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> out = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        JSONObject jsonObject = new JSONObject(out.getBody());
        JSONArray jsonArray = jsonObject.getJSONArray("results");
        List<String> books = new ArrayList<String>();
        for (int n = 0; n < jsonArray.length(); n++){
            books.add((String) jsonArray.getJSONObject(n).getJSONArray("book_details").getJSONObject(0).get("title"));
        }
        return books.toString();
    }
}