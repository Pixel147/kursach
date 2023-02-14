package ua.com.online.appointment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.com.online.appointment.entity.Company;
import ua.com.online.appointment.repository.CompanyRepository;
import ua.com.online.appointment.repository.UserRepository;
import ua.com.online.appointment.response.AppointmentResponse;

@Service
public class AppointmentService {
    @Autowired
    private CompanyRepository companyRepository;
    public ResponseEntity getCompanyAppointmentInfo(int id){
        if(id >= 1){
            Company company = companyRepository.findById(id);
            if(company != null){
                AppointmentResponse response = new AppointmentResponse(companyRepository.findById(id).getName());
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
