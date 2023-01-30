package ua.com.online.appointment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.com.online.appointment.entity.User;
import ua.com.online.appointment.response.OwnerInfoResponse;
import ua.com.online.appointment.service.AuthService;
import ua.com.online.appointment.service.JwtService;
import ua.com.online.appointment.service.UserService;

import javax.servlet.ServletRequest;

@RestController
@CrossOrigin("http://localhost:4200")
public class OwnerController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;
    @Autowired
    private JwtService jwtService;

    @GetMapping("/ownerInfo")
    public ResponseEntity<OwnerInfoResponse> getOwnerInfo(ServletRequest servletRequest)
    {



        User user = jwtService.getUserByToken(servletRequest);
        OwnerInfoResponse ownerInfoResponse = new OwnerInfoResponse();
        ownerInfoResponse.setFullname(user.getFullname());
        ownerInfoResponse.setPhone(user.getPhone());
        ownerInfoResponse.setCompanyName(user.getCompany().getName());
        ownerInfoResponse.setLocation(user.getCompany().getLocation());
        ownerInfoResponse.setDescription(user.getCompany().getDescription());
        ownerInfoResponse.setUsername(user.getUsername());
        System.out.println(ownerInfoResponse.getPhone());
        System.out.println(ownerInfoResponse.getCompanyName());
        System.out.println(ownerInfoResponse.getLocation());
        System.out.println(ownerInfoResponse.getDescription());
        System.out.println(ownerInfoResponse.getFullname());
        System.out.println(ownerInfoResponse.getUsername());
        return new ResponseEntity<OwnerInfoResponse>(ownerInfoResponse, HttpStatus.OK);
    }
}
