package ua.com.online.appointment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.online.appointment.repository.AppointmentRepository;
import ua.com.online.appointment.repository.WorkerRepository;
import ua.com.online.appointment.request.AppointmentRequest;
import ua.com.online.appointment.response.CompanyResponse;
import ua.com.online.appointment.response.FreeTimeResponse;
import ua.com.online.appointment.response.WorkerResponse;
import ua.com.online.appointment.response.WorkerWorkDaysResponse;
import ua.com.online.appointment.service.AppointmentService;

import javax.servlet.ServletRequest;
import java.sql.Date;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/company/{companyId}")
    private ResponseEntity<CompanyResponse> getCompany(@PathVariable Integer companyId){
        CompanyResponse companyResponse = appointmentService.getCompanyInfo(companyId);
        if(companyResponse!= null){
            return new ResponseEntity<>(companyResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/company/{companyId}/workers")
    private ResponseEntity<List<WorkerResponse>> getWorkers(@PathVariable Integer companyId){
        List<WorkerResponse> response = appointmentService.getWorkers(companyId);
        if(response != null){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/worker/{workerId}/workdays")
    private ResponseEntity<WorkerWorkDaysResponse> getWorkerWorkDays(@PathVariable Integer workerId){
        WorkerWorkDaysResponse response = appointmentService.getWorkDays(workerId);
        if(response != null){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/worker/{workerId}/day/{day}")
    private ResponseEntity<List<FreeTimeResponse>> getFreeTimeByDay(@PathVariable Integer workerId,@PathVariable Date day){
        List<FreeTimeResponse> response = appointmentService.getFreeTimeByDay(workerId,day);
        if(response != null){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/register/appointment")
    private HttpStatus createAppointment(ServletRequest servletRequest, @RequestBody AppointmentRequest request){
        Boolean check = appointmentService.createAppointment(servletRequest,request);
        if(!check){
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.CREATED;
    }
}
