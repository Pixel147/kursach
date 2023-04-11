package ua.com.online.appointment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.online.appointment.response.UserAppointmentResponse;
import ua.com.online.appointment.response.WorkerInfoResponse;
import ua.com.online.appointment.response.WorkerJobAppointments;
import ua.com.online.appointment.service.WorkerService;

import javax.servlet.ServletRequest;
import java.sql.Date;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class WorkerController {
    @Autowired
    private WorkerService workerService;

    @GetMapping("/worker")
    public ResponseEntity<WorkerInfoResponse> getWorkerInfo(ServletRequest servletRequest){
        WorkerInfoResponse response = workerService.getWorkerInfo(servletRequest);
        if(response == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/worker/appointment")
    public ResponseEntity<List<UserAppointmentResponse>> getWorkerAppointments(ServletRequest servletRequest){
        List<UserAppointmentResponse> response = workerService.getAppointments(servletRequest);
        if(response == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(response.size() == 0){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/worker/company/appointment/{day}")
    public ResponseEntity<List<WorkerJobAppointments>> getWeeklyJobAppointments(ServletRequest servletRequest, @PathVariable Date day){
        List<WorkerJobAppointments> response = workerService.getJobAppointments(servletRequest,day.toLocalDate());
        if(response == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else if(response.size() == 0){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PutMapping("/worker/appointment/{idAppointment}")
    public ResponseEntity<String> cancelAppointment(@PathVariable Integer idAppointment,@RequestBody String status, ServletRequest servletRequest){
        if(workerService.cancelAppointment(idAppointment,status,servletRequest) == null){
            return new ResponseEntity<>("APPOINTMENT NOT FOUND", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
