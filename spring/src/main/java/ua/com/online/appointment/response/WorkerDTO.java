package ua.com.online.appointment.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WorkerDTO {
    private int id;
    private String username;
    private String email;
    private String phone;
    private String fullname;
    private String service;
}
