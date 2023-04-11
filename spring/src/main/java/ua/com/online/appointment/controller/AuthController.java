package ua.com.online.appointment.controller;

import ua.com.online.appointment.request.AuthRequest;
import ua.com.online.appointment.request.ClientRegistrationRequest;
import ua.com.online.appointment.request.OwnerRegistrationRequest;
import ua.com.online.appointment.request.WorkerRegistrationRequest;
import ua.com.online.appointment.response.AuthResponse;
import ua.com.online.appointment.response.RegistrationResponse;
import ua.com.online.appointment.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

@RestController
@CrossOrigin("http://localhost:4200")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/register/owner")
    public ResponseEntity<RegistrationResponse> registerOwner(@RequestBody @Valid OwnerRegistrationRequest ownerRegistrationRequest) {
        RegistrationResponse response = authService.createCompanyAndOwner(ownerRegistrationRequest);
        if(response == null){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/register/worker")
    public ResponseEntity<RegistrationResponse> registerWorker(@RequestBody @Valid WorkerRegistrationRequest workerRegistrationRequest, ServletRequest servletRequest) {
        RegistrationResponse response = authService.createWorker(workerRegistrationRequest,servletRequest);
        if(response == null){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/register/client")
    public ResponseEntity<RegistrationResponse> registerClient(@RequestBody ClientRegistrationRequest request){
        RegistrationResponse response = authService.createClient(request);
        if(response == null){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        AuthResponse response = authService.login(request);
        if(response != null){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
