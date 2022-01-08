package com.example.springproj12;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class BadVersionController {

    @GetMapping("/createdata")
    public String createTestData(Integer dataSize) throws IOException {
        int firstBad = ThreadLocalRandom.current().nextInt(1, dataSize + 1);
        StringBuilder dataBuilder = new StringBuilder("");
        for (int i=1; i < dataSize + 1; i++) {
            if (i >= firstBad) {
                dataBuilder.append("bad,");
            } else {
                dataBuilder.append("good,");
            }
        }
        String data = dataBuilder.substring(0, dataBuilder.length() - 1);
        BufferedWriter writer = new BufferedWriter(new FileWriter("mock_data_versions.txt"));
        writer.write(data);
        writer.close();
        return String.format("Test data created successfully with first bad version %d", firstBad);
    }

    @PostMapping("/isbadversionlinear")
    public Integer isbadversionlinear(@RequestBody String data) {
        List<String> dataList = Arrays.asList(data.split(","));
        for (int i=0; i < dataList.size(); i++) {
            String version = dataList.get(i);
            if (version.equals("bad")) {
                return i + 1;
            }
        }
        return 0;
    }

    @PostMapping("/isbadversionbinary")
    public Integer isbadversionbinary(@RequestBody String data) {
        List<String> dataList = Arrays.asList(data.split(","));
        int left = 1;
        int right = dataList.size();
        while  (left < right) {
            int mid = left + (right - left) / 2;
            if (dataList.get(mid - 1).equals("bad")) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}