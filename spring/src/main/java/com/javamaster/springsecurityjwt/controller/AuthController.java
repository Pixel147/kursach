package com.javamaster.springsecurityjwt.controller;

import com.javamaster.springsecurityjwt.config.jwt.JwtProvider;
import com.javamaster.springsecurityjwt.entity.User;

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
    public ResponseEntity<RegistrationResponse> registerOwner(@RequestBody @Valid RegistrationRequest registrationRequest) {
        return authService.createCompanyAndOwner(registrationRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        User user = userService.findByLoginAndPassword(request.getUsername(), request.getPassword());
        if(user == null){
            return new ResponseEntity<AuthResponse>(new AuthResponse("invalid data","invalid data"),HttpStatus.BAD_REQUEST);
        }
        String token = jwtProvider.generateToken(user.getUsername());
        return new ResponseEntity<AuthResponse>(new AuthResponse(user.getRole(),token),HttpStatus.OK);
    }

}
