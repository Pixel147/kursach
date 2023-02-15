package ua.com.online.appointment.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WorkerWorkDaysResponse {
    private boolean monday;
    private boolean tuesday;
    private boolean wednesday;
    private boolean thursday;
    private boolean friday;
    private boolean saturday;
    private boolean sunday;
    public WorkerWorkDaysResponse(){
        this.monday = false;
        this.tuesday = false;
        this.wednesday = false;
        this.thursday = false;
        this.friday = false;
        this.saturday = false;
        this.sunday = false;
    }
}
