package com.javamaster.springsecurityjwt.controller;

import com.javamaster.springsecurityjwt.config.jwt.JwtProvider;
import com.javamaster.springsecurityjwt.entity.UserEntity;

import com.javamaster.springsecurityjwt.request.AuthRequest;
import com.javamaster.springsecurityjwt.request.RegistrationRequest;
import com.javamaster.springsecurityjwt.response.AuthResponse;
import com.javamaster.springsecurityjwt.response.RegistrationResponse;
import com.javamaster.springsecurityjwt.service.AuthService;
import com.javamaster.springsecurityjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("http://localhost:4200")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/register/owner")
    public ResponseEntity registerOwner(@RequestBody @Valid RegistrationRequest registrationRequest) {
        RegistrationResponse validation = authService.registerValidation(registrationRequest);
        if(validation == null)
        {
            authService.saveNewOwner(registrationRequest);
            return new ResponseEntity("created",HttpStatus.CREATED);
        }
        return new ResponseEntity(validation,HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthRequest request) {
        UserEntity userEntity = userService.findByLoginAndPassword(request.getUsername(), request.getPassword());
        if(userEntity == null){
            return new ResponseEntity("invalid data",HttpStatus.BAD_REQUEST);
        }
        String token = jwtProvider.generateToken(userEntity.getUsername());
        return new ResponseEntity(new AuthResponse(userEntity.getRole(),token),HttpStatus.OK);
    }

}
