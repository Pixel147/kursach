package ua.com.online.appointment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.online.appointment.response.AppointmentClientResponse;
import ua.com.online.appointment.response.ClientResponse;
import ua.com.online.appointment.service.ClientService;

import javax.servlet.ServletRequest;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/client")
    public ResponseEntity<ClientResponse> getClient(ServletRequest servletRequest){
        ClientResponse response = clientService.getUser(servletRequest);
        if(response == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping("/client/appointment")
    public ResponseEntity getAppointments(ServletRequest servletRequest){
        List<AppointmentClientResponse> response = clientService.getAppointments(servletRequest);
        if(response == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(response.size() == 0){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @DeleteMapping("client/appointment/delete/{idAppointment}")
    public void deleteAppointment(@PathVariable Integer idAppointment,ServletRequest servletRequest){
        clientService.deleteAppointment(idAppointment,servletRequest);
    }
}
