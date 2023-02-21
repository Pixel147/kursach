package ua.com.online.appointment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ua.com.online.appointment.response.CompanyResponse;
import ua.com.online.appointment.response.WorkerAndServiceResponse;
import ua.com.online.appointment.response.WorkerWorkDaysResponse;
import ua.com.online.appointment.service.AppointmentService2;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class AppointmentController {
    @Autowired
    private AppointmentService2 appointmentService;
    @GetMapping("/company/{id}")
    private ResponseEntity<CompanyResponse> getCompanyAppointment(@PathVariable Integer id){
        CompanyResponse companyResponse = appointmentService.getCompanyAppointmentInfo(id);
        if(companyResponse!= null){
            return new ResponseEntity<>(companyResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/company/{id}/services")
    private ResponseEntity<List<WorkerAndServiceResponse>> getCompanyServices(@PathVariable Integer id){
        List<WorkerAndServiceResponse> response = appointmentService.getWorkersAndServices(id);
        if(response != null){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/worker/{id}/workdays")
    private ResponseEntity<WorkerWorkDaysResponse> getWorkerWorkDays(@PathVariable Integer id){
        WorkerWorkDaysResponse response = appointmentService.getWorkDays(id);
        if(response != null){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
