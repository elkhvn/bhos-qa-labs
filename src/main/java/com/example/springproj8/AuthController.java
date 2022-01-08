package com.example.springproj8;

import com.nimbusds.srp6.SRP6Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/step1")
    public String step1(@RequestBody String username) {
        return authService.step1(username);
    }

    @PostMapping("/step2")
    public String step2(@RequestBody Step2Body step2Body) throws SRP6Exception {
        return authService.step2(step2Body.A, step2Body.M1);
    }

    @GetMapping("/loadTest")
    public ResponseEntity<Object> userSimulation() {
        String response = authService.simulation();
        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN ).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }
}