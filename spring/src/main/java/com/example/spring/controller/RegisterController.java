package com.example.spring.controller;

import com.example.spring.entity.User;
import com.example.spring.repository.UserRepository;
import com.example.spring.response.RegisterBadResponse;
import com.example.spring.response.RegisterResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity createUser(@RequestBody User user) throws Exception {
        User userByUsername = userRepository.findByUsername(user.getUsername());
        User userByEmail = userRepository.findByEmail(user.getEmail());
        if(userByUsername == null && userByEmail == null)
        {
            //CREATE
            RegisterResponse registerResponse = new RegisterResponse(user.getUsername(), user.getEmail(), user.getPassword(), user.getUserType());
            user.setPassword(AESEncryption.encrypt(user.getPassword()));
            userRepository.save(user);
            return new ResponseEntity<>(registerResponse,HttpStatus.CREATED);
        }
        String messageEmail = "ok";
        String messageUsername = "ok";
        if(userByUsername != null)
        {
            messageUsername = "validation.username.found";
        }
        if(userByEmail != null)
        {
            messageEmail = "validation.email.found";
        }
        RegisterBadResponse registerBadResponse = new RegisterBadResponse(2,messageEmail,messageUsername);
        return new ResponseEntity<>(registerBadResponse,HttpStatus.BAD_REQUEST);
    }
}
