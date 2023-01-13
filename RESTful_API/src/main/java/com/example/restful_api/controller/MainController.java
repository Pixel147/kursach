package com.example.restful_api.controller;

import com.example.restful_api.entity.LoginRequest;
import com.example.restful_api.entity.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
public class MainController {
    @GetMapping("/")
    public ResponseEntity main()
    {
        return ResponseEntity.ok("bingo");
    }

    @GetMapping("/task")
    public ResponseEntity task()
    {
        return ResponseEntity.ok(new Task(1l, "Купить хлеб"));
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        System.out.println(username + " " + password);
        System.out.println(HttpStatus.OK);
        return new ResponseEntity<>("Login successful", HttpStatus.OK);
    }
}
