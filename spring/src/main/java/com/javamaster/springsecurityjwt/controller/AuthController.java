package com.javamaster.springsecurityjwt.controller;

import com.javamaster.springsecurityjwt.config.jwt.JwtProvider;
import com.javamaster.springsecurityjwt.entity.CompanyEntity;
import com.javamaster.springsecurityjwt.entity.UserEntity;
import com.javamaster.springsecurityjwt.repository.CompanyEntityRepository;
import com.javamaster.springsecurityjwt.repository.UserEntityRepository;
import com.javamaster.springsecurityjwt.request.AuthRequest;
import com.javamaster.springsecurityjwt.request.RegistrationRequest;
import com.javamaster.springsecurityjwt.response.AuthResponse;
import com.javamaster.springsecurityjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@CrossOrigin("http://localhost:4200")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private CompanyEntityRepository companyEntityRepository;

    @PostMapping("/register/owner")
    public String registerOwner(@RequestBody @Valid RegistrationRequest registrationRequest) {
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setName(registrationRequest.getCompany_name());
        companyEntity.setLocation(registrationRequest.getLocation());
        companyEntityRepository.save(companyEntity);

        UserEntity userEntity = new UserEntity();
        userEntity.setCompanyEntity(companyEntity);
        userEntity.setRole("ROLE_OWNER");
        userEntity.setUsername(registrationRequest.getUsername());
        userEntity.setEmail(registrationRequest.getEmail());
        userEntity.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        userEntity.setFullname(registrationRequest.getFullname());
        userEntity.setPhone(registrationRequest.getPhone());

        userEntityRepository.save(userEntity);
        //RegistrationResponse response = new RegistrationResponse("notFound","notFound");
        //return new ResponseEntity(response,HttpStatus.CREATED);
        return "ok";
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        UserEntity userEntity = userService.findByLoginAndPassword(request.getUsername(), request.getPassword());
        String token = jwtProvider.generateToken(userEntity.getUsername());
        return new AuthResponse(token);
    }

//    @PostMapping("/user_type")
//    public String userType(@RequestBody AuthResponse request) {
//        todo mb use doFilter
//    }

}
