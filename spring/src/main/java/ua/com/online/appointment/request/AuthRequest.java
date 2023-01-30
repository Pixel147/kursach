package ua.com.online.appointment.request;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
