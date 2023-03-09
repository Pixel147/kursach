package ua.com.online.appointment.response;

import lombok.Data;

@Data
public class WorkerJobAppointments {
    private int id;
    private String clientName;
    private String clientPhone;
    private String dateStart;
    private String dateEnd;
    private String service;
}
