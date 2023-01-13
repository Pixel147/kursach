package com.example.restful_api.controller;

import com.example.restful_api.entity.LoginRequest;
import com.example.restful_api.entity.RegisterRequest;
import com.example.restful_api.entity.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
public class MainController {

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        System.out.println(username + " " + password);
        return new ResponseEntity<>("Login successful", HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        String username = registerRequest.getUsername();
        String email = registerRequest.getEmail();
        String password = registerRequest.getPassword();
        String type = registerRequest.getType();
        System.out.println(username + " " + email + " " + password + " " + type);
        return new ResponseEntity<>("register successful", HttpStatus.OK);
    }
}
