package ua.com.online.appointment.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class EditWorkerRequest {
    @NotEmpty
    private String editEmail;
    @NotEmpty
    private String editPhone;
    @NotEmpty
    private String editFullname;
}
