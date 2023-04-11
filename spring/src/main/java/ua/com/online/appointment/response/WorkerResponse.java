package ua.com.online.appointment.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WorkerResponse {
    private Integer id;
    private String fullname;
    private String service;
}
