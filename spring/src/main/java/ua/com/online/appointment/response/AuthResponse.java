package ua.com.online.appointment.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private int id;
    private String role;
    private String token;
}
