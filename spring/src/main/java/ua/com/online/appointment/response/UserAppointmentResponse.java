package ua.com.online.appointment.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserAppointmentResponse {
    private int id;
    private String companyName;
    private String workerFullName;
    private String service;
    private String workerPhone;
    private LocalDate date;
    private String time;
}
