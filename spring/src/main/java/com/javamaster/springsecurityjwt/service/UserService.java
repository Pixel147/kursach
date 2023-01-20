package com.javamaster.springsecurityjwt.service;

import com.javamaster.springsecurityjwt.controller.RegistrationRequest;
import com.javamaster.springsecurityjwt.entity.RoleEntity;
import com.javamaster.springsecurityjwt.entity.UserEntity;
import com.javamaster.springsecurityjwt.repository.RoleEntityRepository;
import com.javamaster.springsecurityjwt.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private RoleEntityRepository roleEntityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity saveUser(RegistrationRequest registrationRequest) {
        RoleEntity userRole = roleEntityRepository.findById(registrationRequest.getType());
        UserEntity userEntity = new UserEntity();
        userEntity.setRoleEntity(userRole);
        userEntity.setUsername(registrationRequest.getUsername());
        userEntity.setEmail(registrationRequest.getEmail());
        userEntity.setPassword(registrationRequest.getPassword());
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userEntityRepository.save(userEntity);
    }

    public UserEntity findByUsername(String username) {
        return userEntityRepository.findByUsername(username);
    }

    public UserEntity findByLoginAndPassword(String username, String password) {
        UserEntity userEntity = findByUsername(username);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }
}
