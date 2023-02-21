package ua.com.online.appointment.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;

@Data
@AllArgsConstructor
public class FreeTimeResponse {
    private Time time;
}
