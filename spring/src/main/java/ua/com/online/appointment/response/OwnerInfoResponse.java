package ua.com.online.appointment.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerInfoResponse {
        private String fullname;
        private String companyName;
        private String phone;
}
