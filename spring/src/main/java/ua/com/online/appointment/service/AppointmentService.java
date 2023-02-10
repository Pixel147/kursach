package ua.com.online.appointment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.com.online.appointment.repository.CompanyRepository;
import ua.com.online.appointment.repository.UserRepository;
import ua.com.online.appointment.response.AppointmentResponse;

@Service
public class AppointmentService {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;
    public ResponseEntity getCompanyAppointmentInfo(String name){
        AppointmentResponse response = new AppointmentResponse(companyRepository.findByName(name).getName());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
