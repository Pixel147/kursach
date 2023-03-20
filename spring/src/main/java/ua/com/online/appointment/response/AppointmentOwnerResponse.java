package ua.com.online.appointment.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AppointmentOwnerResponse {
    private String companyName;
    private String workerFullname;
    private String service;
    private String workerPhone;
    private String date;
    private String time;
}
