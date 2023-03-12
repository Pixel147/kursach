package ua.com.online.appointment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.online.appointment.response.UserAppointmentResponse;
import ua.com.online.appointment.response.ClientInfoResponse;
import ua.com.online.appointment.service.ClientService;

import javax.servlet.ServletRequest;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/client")
    public ResponseEntity<ClientInfoResponse> getClient(ServletRequest servletRequest){
        ClientInfoResponse response = clientService.getUser(servletRequest);
        if(response == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping("/client/appointment")
    public ResponseEntity<List<UserAppointmentResponse>> getClientAppointments(ServletRequest servletRequest){
        List<UserAppointmentResponse> response = clientService.getAppointments(servletRequest);
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
