package ua.com.online.appointment.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WorkerDTO {
    //TODO: якось цей клас дивнов виглядає. Він мав бибути в пакеті респонсе? цей пакет і клас вибивається
    private int id;
    private String username;
    private String email;
    private String phone;
    private String fullname;

    private String service;
}
