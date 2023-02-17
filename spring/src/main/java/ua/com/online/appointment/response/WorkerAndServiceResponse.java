package ua.com.online.appointment.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WorkerAndServiceResponse {
    private Integer id;
    private String username;
    private String service;
}
