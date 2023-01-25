package com.javamaster.springsecurityjwt.service;

import com.javamaster.springsecurityjwt.entity.Company;
import com.javamaster.springsecurityjwt.entity.User;
import com.javamaster.springsecurityjwt.repository.CompanyEntityRepository;
import com.javamaster.springsecurityjwt.repository.UserEntityRepository;
import com.javamaster.springsecurityjwt.request.RegistrationRequest;
import com.javamaster.springsecurityjwt.response.RegistrationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private CompanyEntityRepository companyEntityRepository;
    private RegistrationResponse registerValidation(RegistrationRequest registrationRequest){
        User validationEmail = userEntityRepository.findByEmail(registrationRequest.getEmail());
        User validationUsername = userEntityRepository.findByUsername(registrationRequest.getUsername());
        User validationPhone = userEntityRepository.findByPhone(registrationRequest.getPhone());
        Company validationCompanyName = companyEntityRepository.findByName(registrationRequest.getCompany_name());
        if(validationEmail != null || validationUsername != null || validationPhone != null || validationCompanyName != null){
            return new RegistrationResponse(
                    validationUsername != null ? "validation.username.found" : "ok",
                    validationEmail != null ? "validation.email.found" : "ok",
                    validationCompanyName != null ? "validation.company.found" : "ok",
                    validationPhone != null ? "validation.phone.found" : "ok"
            );
        }
        return null;
    }
    public ResponseEntity<RegistrationResponse> createCompanyAndOwner(RegistrationRequest registrationRequest){
        RegistrationResponse validation = registerValidation(registrationRequest);
        if(validation == null)
        {
            Company company = new Company();
            company.setName(registrationRequest.getCompany_name());
            company.setLocation(registrationRequest.getLocation());
            companyEntityRepository.save(company);
            User user = new User();
            user.setCompany(company);
            user.setRole("ROLE_OWNER");
            user.setUsername(registrationRequest.getUsername());
            user.setEmail(registrationRequest.getEmail());
            user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            user.setFullname(registrationRequest.getFullname());
            user.setPhone(registrationRequest.getPhone());
            userEntityRepository.save(user);
            return new ResponseEntity<RegistrationResponse>(
                    new RegistrationResponse("ok","ok","ok","ok"),
                    HttpStatus.CREATED
            );
        }
        return new ResponseEntity<RegistrationResponse>(validation,HttpStatus.BAD_REQUEST);
    }
}
