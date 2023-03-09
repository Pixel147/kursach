package ua.com.online.appointment.response;

import lombok.Data;

@Data
public class UserInfoResponse {
    private String username;
    private String fullname;
    private String email;
    private String phone;
}
