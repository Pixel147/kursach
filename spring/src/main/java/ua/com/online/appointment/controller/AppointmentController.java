package ua.com.online.appointment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ua.com.online.appointment.service.AppointmentService;

@RestController
@CrossOrigin("http://localhost:4200")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    @GetMapping("/company/{id}")
    private ResponseEntity getCompanyAppointment(@PathVariable int id){
        return appointmentService.getCompanyAppointmentInfo(id);
    }
    @GetMapping("/company/{id}/services")
    private ResponseEntity getCompanyServices(@PathVariable int id){
        return appointmentService.getCompanyServices(id);
    }
    @GetMapping("/company/{id}/worker/{service}")
    private ResponseEntity getWorkerByService(@PathVariable int id, @PathVariable String service){
        return appointmentService.getWorkerByCompanyAndService(id,service);
    }
}
