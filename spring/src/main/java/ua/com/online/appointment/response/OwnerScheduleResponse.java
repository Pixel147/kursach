package ua.com.online.appointment.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class OwnerScheduleResponse {
    private String companyName;
    private String workerFullname;
    private String service;
    private String workerPhone;
    private String date;
    private String time;
    private String clientFullname;
    private String clientPhone;
}
