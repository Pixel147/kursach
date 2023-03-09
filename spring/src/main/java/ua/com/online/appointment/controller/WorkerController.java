package ua.com.online.appointment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.online.appointment.response.UserAppointmentResponse;
import ua.com.online.appointment.response.UserInfoResponse;
import ua.com.online.appointment.service.WorkerService;

import javax.servlet.ServletRequest;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class WorkerController {
    @Autowired
    private WorkerService workerService;

    @GetMapping("/worker")
    public ResponseEntity<UserInfoResponse> getWorkerInfo(ServletRequest servletRequest){
        UserInfoResponse response = workerService.getWorkerInfo(servletRequest);
        if(response == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/worker/appointment")
    public ResponseEntity getWorkerAppointments(ServletRequest servletRequest){
        List<UserAppointmentResponse> response = workerService.getAppointments(servletRequest);
        if(response == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(response.size() == 0){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
