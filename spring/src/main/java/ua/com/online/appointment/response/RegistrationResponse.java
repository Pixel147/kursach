package ua.com.online.appointment.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationResponse {

    private String usernameMessage;
    private String emailMessage;
    private String companyNameMessage;
    private String phoneMessage;
}
