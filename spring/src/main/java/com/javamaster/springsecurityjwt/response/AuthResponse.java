package com.javamaster.springsecurityjwt.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String role;
    private String token;
}
