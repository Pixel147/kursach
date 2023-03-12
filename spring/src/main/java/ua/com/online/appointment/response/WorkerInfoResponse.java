package ua.com.online.appointment.response;

import lombok.Data;

@Data
public class WorkerInfoResponse {
    private String username;
    private String fullname;
    private String email;
    private String phone;
    private String workStartsIn;
    private String workEndsIn;
    private Integer mondayFlag;
    private Integer tuesdayFlag;
    private Integer wednesdayFlag;
    private Integer thursdayFlag;
    private Integer fridayFlag;
    private Integer saturdayFlag;
    private Integer sundayFlag;
}
