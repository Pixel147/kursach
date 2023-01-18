package com.example.spring.controller;

import com.example.spring.entity.User;
import com.example.spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200/")
public class LoginConroller {

    private final UserRepository userRepository;
    @PostMapping
    public LoginResponse loginRequest(@RequestBody User user)
    {
        User wantLogin = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        LoginResponse returnUser = new LoginResponse(wantLogin.getUsername(),wantLogin.getUserType());
        if(wantLogin == null)
        {
            return null;
        }
        else
        {
            return returnUser;
        }
    }
}
