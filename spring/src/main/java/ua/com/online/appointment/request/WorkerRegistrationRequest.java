package ua.com.online.appointment.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class WorkerRegistrationRequest {
    @NotEmpty
    private String username;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String fullname;
    @NotEmpty
    private String phone;
    @NotEmpty
    private String role;
}
