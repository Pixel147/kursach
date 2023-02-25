package ua.com.online.appointment.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class FreeTimeResponse {
    private LocalTime time;
}
