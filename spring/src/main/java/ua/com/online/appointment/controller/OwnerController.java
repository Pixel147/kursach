package ua.com.online.appointment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.online.appointment.DTO.WorkerDTO;
import java.util.*;

import ua.com.online.appointment.entity.Company;
import ua.com.online.appointment.entity.User;
import ua.com.online.appointment.entity.Worker;
import ua.com.online.appointment.repository.CompanyRepository;
import ua.com.online.appointment.repository.UserRepository;
import ua.com.online.appointment.repository.WorkerRepository;
import ua.com.online.appointment.request.EditWorkerRequest;
import ua.com.online.appointment.response.OwnerInfoResponse;
import ua.com.online.appointment.service.AuthService;
import ua.com.online.appointment.service.JwtService;
import ua.com.online.appointment.service.OwnerService;
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
    @Autowired
    private OwnerService ownerService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkerRepository workerRepository;

    @GetMapping("/owner/{id}")
    public ResponseEntity<OwnerInfoResponse> getOwnerInfo(@PathVariable int id,ServletRequest servletRequest)
    {
        return ownerService.returnOwnerInfo(id,servletRequest);
    }

<<<<<<< HEAD
    @GetMapping("/ownerWorkers")
    public ResponseEntity getWorkers(ServletRequest servletRequest){
        User user = jwtService.getUserByToken(servletRequest);
        List<User>userList = userRepository.getUsersByCompanyAndAndRole(user.getCompany(),"ROLE_WORKER");
        List<WorkerDTO>workerDTOS = new ArrayList<>();
        for (User u:userList) {
            workerDTOS.add(new WorkerDTO(u.getId(),u.getUsername(),u.getEmail(),u.getPhone(),u.getFullname(), u.getWorker().getService()));
        }
        return new ResponseEntity<>(workerDTOS,HttpStatus.OK);
=======
    @GetMapping("/owner/{id}/workers")
    public ResponseEntity getWorkers(@PathVariable int id, ServletRequest servletRequest){
        return ownerService.returnWorkersCompany(id,servletRequest);
>>>>>>> develop
    }

    @DeleteMapping("/worker{id}")
    public void deleteWorker(@PathVariable int id){
        User user = userRepository.findById(id);
        if(user != null){
            Worker worker = user.getWorker();
            userRepository.delete(user);
            workerRepository.delete(worker);
            System.out.println("deleted user id = " + id);
        }
    }
    @PutMapping("/owner/{id}/description")
    public HttpStatus updateDescription(@PathVariable int id,@RequestBody String description, ServletRequest servletRequest){
        return ownerService.updateCompanyDescription(id,servletRequest,description);
    }

    @PutMapping("/owner/edit")
    public HttpStatus updateDescriptionWorker(@RequestBody EditWorkerRequest editWorkerRequest, ServletRequest servletRequest){
        User user = jwtService.getUserByToken(servletRequest);
        user.setEmail(editWorkerRequest.getEditEmail());
        user.setPhone(editWorkerRequest.getEditPhone());
        user.setFullname(editWorkerRequest.getEditFullname());
        userRepository.save(user);
        System.out.println("Worker updated");
        return HttpStatus.OK;
    }
}
