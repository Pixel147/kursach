package ua.com.online.appointment.request;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentRequest {
    private LocalDate date;
    private LocalTime time;
    private Integer workerId;
}
