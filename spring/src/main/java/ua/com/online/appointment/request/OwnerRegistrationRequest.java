package ua.com.online.appointment.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OwnerRegistrationRequest {

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String email;
    @NotEmpty
    private String fullname;
    @NotEmpty
    private String location;
    @NotEmpty
    private String phone;
    @NotEmpty
    private String company_name;
    @NotEmpty
    private String role;

}
