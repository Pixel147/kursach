package ua.com.online.appointment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ua.com.online.appointment.response.CompanyResponse;
import ua.com.online.appointment.service.AppointmentService;

@RestController
@CrossOrigin("http://localhost:4200")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    @GetMapping("/company/{id}")
    private ResponseEntity<CompanyResponse> getCompanyAppointment(@PathVariable Integer id){
        return appointmentService.getCompanyAppointmentInfo(id);
    }
    @GetMapping("/company/{id}/services")
    private ResponseEntity getCompanyServices(@PathVariable Integer id){
        return appointmentService.getCompanyServices(id);
    }
    @GetMapping("/company/{id}/worker/{service}")
    private ResponseEntity getWorkerByService(@PathVariable Integer id, @PathVariable String service){
        return appointmentService.getWorkerByCompanyAndService(id,service);
    }
    @GetMapping("/company/{id}/worker/{username}/days")
    private ResponseEntity getWorkerWorkDays(@PathVariable Integer id, @PathVariable String username){
        return appointmentService.getWorkDays(id,username);
    }
}
