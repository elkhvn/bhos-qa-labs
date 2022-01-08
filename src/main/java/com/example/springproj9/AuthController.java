package com.example.springproj9;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = new ArrayList<>(userRepository.findAll());
            return ResponseEntity.status(HttpStatus.OK).body(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestParam MultiValueMap<String,String> paramMap) {
        try {
            String email = paramMap.get("email").get(0);
            String password = paramMap.get("password").get(0);
            User newUser = new User(email, password);
            User _user = userRepository.save(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(_user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/mock")
    public List<String> mockData(@RequestParam Integer size) throws IOException {
        MockNeat m = MockNeat.threadLocal();

        MockUnitInt num = m.probabilites(Integer.class)
                .add(0.3, m.ints().range(0, 10))
                .add(0.7, m.ints().range(10, 20))
                .mapToInt(Integer::intValue);
        List<String> strings = m.fmt("#{email},#{password}")
                .param("email", m.emails())
                .param("password", m.passwords())
                .list(size)
                .val();
        BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("mock_data_%d.csv", size)));
        writer.write("email,password");
        writer.write("\n");
        for (String line : strings) {
            writer.write(line);
            writer.write("\n");
        }
        writer.close();

        return strings;
    }
}