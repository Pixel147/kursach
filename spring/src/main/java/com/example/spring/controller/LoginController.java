package com.example.spring.controller;

import com.example.spring.config.AESDecryption;
import com.example.spring.config.AESEncryption;
import com.example.spring.entity.User;
import com.example.spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200/")
public class LoginController {
    @Autowired
    private UserRepository userRepository;
    private AESEncryption aesEncryption;
    private AESDecryption aesDecryption;

    @PostMapping
    public LoginResponse loginRequest(@RequestBody User user) throws Exception {
        User dbUser = userRepository.findByUsername(user.getUsername());
        if (dbUser == null)
        {
            return null;
        }
        LoginResponse returnUser = new LoginResponse(dbUser.getUsername(), dbUser.getUserType());

        if (aesEncryption.encrypt(user.getPassword()).equals(dbUser.getPassword()))
        {
            return returnUser;
        }
        else
        {
            return null;
        }

    }
}

