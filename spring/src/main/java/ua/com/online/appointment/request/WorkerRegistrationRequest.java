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
    private String service;
    @NotEmpty
    private String role;
    private int mondayStart;
    private int mondayEnd;
    private int tuesdayStart;
    private int tuesdayEnd;
    private int wednesdayStart;
    private int wednesdayEnd;
    private int thursdayStart;
    private int thursdayEnd;
    private int fridayStart;
    private int fridayEnd;
    private int saturdayStart;
    private int saturdayEnd;
    private int sundayStart;
    private int sundayEnd;
}
