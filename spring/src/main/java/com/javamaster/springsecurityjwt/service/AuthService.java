package com.javamaster.springsecurityjwt.service;

import com.javamaster.springsecurityjwt.entity.CompanyEntity;
import com.javamaster.springsecurityjwt.entity.UserEntity;
import com.javamaster.springsecurityjwt.repository.CompanyEntityRepository;
import com.javamaster.springsecurityjwt.repository.UserEntityRepository;
import com.javamaster.springsecurityjwt.request.RegistrationRequest;
import com.javamaster.springsecurityjwt.response.RegistrationResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
    public RegistrationResponse registerValidation(RegistrationRequest registrationRequest){
        UserEntity validationEmail = userEntityRepository.findByEmail(registrationRequest.getEmail());
        UserEntity validationUsername = userEntityRepository.findByUsername(registrationRequest.getUsername());
        UserEntity validationPhone = userEntityRepository.findByPhone(registrationRequest.getPhone());
        CompanyEntity validationCompanyName = companyEntityRepository.findByName(registrationRequest.getCompany_name());
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
    public void saveNewOwner(RegistrationRequest registrationRequest){
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
    }
}
