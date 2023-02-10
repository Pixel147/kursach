package ua.com.online.appointment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ua.com.online.appointment.service.AppointmentService;
import ua.com.online.appointment.service.AuthService;
import ua.com.online.appointment.service.JwtService;
import ua.com.online.appointment.service.UserService;

@RestController
@CrossOrigin("http://localhost:4200")
public class AppointmentController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AppointmentService appointmentService;
    @GetMapping("/company/{name}")
    private ResponseEntity getCompanyAppointment(@PathVariable String name){
        if(name != null){
            return appointmentService.getCompanyAppointmentInfo(name);
        }
        return new ResponseEntity<>("NotFound", HttpStatus.NOT_FOUND);
    }
}
