package com.example.spring.controller;

import com.example.spring.entity.User;
import com.example.spring.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.spring.config.*;

@RestController
@CrossOrigin("http://localhost:4200")
@NoArgsConstructor
public class RegisterController {
    @Autowired
    private UserRepository userRepository;
    private AESEncryption aesEncryption;
    private AESDecryption aesDecryption;

    public RegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public HttpStatus createUser(@RequestBody User user) throws Exception {
        user.setPassword(aesEncryption.encrypt(user.getPassword()));
        userRepository.save(user);
        return HttpStatus.CREATED;
    }
}
