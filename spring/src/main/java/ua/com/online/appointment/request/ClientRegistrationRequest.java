package ua.com.online.appointment.request;

import lombok.Data;

@Data
public class ClientRegistrationRequest {
    private String username;
    private String password;
    private String phone;
    private String email;
    private String fullname;
}
