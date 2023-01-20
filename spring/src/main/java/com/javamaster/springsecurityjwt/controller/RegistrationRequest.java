package com.javamaster.springsecurityjwt.controller;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RegistrationRequest {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
    @NotEmpty
    private String email;
    @NotNull
    private int type;
}
