package ua.com.online.appointment.controller;

import ua.com.online.appointment.config.jwt.JwtProvider;
import ua.com.online.appointment.entity.User;

import ua.com.online.appointment.request.AuthRequest;
import ua.com.online.appointment.request.OwnerRegistrationRequest;
import ua.com.online.appointment.request.WorkerRegistrationRequest;
import ua.com.online.appointment.response.AuthResponse;
import ua.com.online.appointment.response.RegistrationResponse;
import ua.com.online.appointment.service.AuthService;
import ua.com.online.appointment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("http://localhost:4200")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/register/owner")
    public ResponseEntity<RegistrationResponse> registerOwner(@RequestBody @Valid OwnerRegistrationRequest ownerRegistrationRequest) {
        return authService.createCompanyAndOwner(ownerRegistrationRequest);
    }

    @PostMapping("/register/worker")
    public ResponseEntity<RegistrationResponse> registerWorker(@RequestBody @Valid WorkerRegistrationRequest workerRegistrationRequest) {
        return authService.createWorker(workerRegistrationRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        User user = userService.findByLoginAndPassword(request.getUsername(), request.getPassword());
        if (user == null) {
            return new ResponseEntity<AuthResponse>(new AuthResponse("invalid data", "invalid data"), HttpStatus.BAD_REQUEST);
        }
        String token = jwtProvider.generateToken(user.getUsername());
        return new ResponseEntity<AuthResponse>(new AuthResponse(user.getRole(), token), HttpStatus.OK);
    }

}
