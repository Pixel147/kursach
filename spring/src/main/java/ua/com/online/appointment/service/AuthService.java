package ua.com.online.appointment.service;

import ua.com.online.appointment.entity.Company;
import ua.com.online.appointment.entity.User;
import ua.com.online.appointment.entity.Worker;
import ua.com.online.appointment.repository.CompanyRepository;
import ua.com.online.appointment.repository.UserRepository;
import ua.com.online.appointment.repository.WorkerRepository;
import ua.com.online.appointment.request.OwnerRegistrationRequest;
import ua.com.online.appointment.request.WorkerRegistrationRequest;
import ua.com.online.appointment.response.RegistrationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private CompanyRepository companyRepository;
    private RegistrationResponse registerOwnerValidation(OwnerRegistrationRequest ownerRegistrationRequest){
        User validationEmail = userRepository.findByEmail(ownerRegistrationRequest.getEmail());
        User validationUsername = userRepository.findByUsername(ownerRegistrationRequest.getUsername());
        User validationPhone = userRepository.findByPhone(ownerRegistrationRequest.getPhone());
        Company validationCompanyName = companyRepository.findByName(ownerRegistrationRequest.getCompany_name());
        if(validationEmail != null || validationUsername != null || validationPhone != null || validationCompanyName != null){
            return new RegistrationResponse(
                    validationUsername != null ? "validation.username.found" : "ok",
                    validationEmail != null ? "validation.email.found" : "ok",
                    validationCompanyName != null ? "validation.company.found" : "ok",
                    validationPhone != null ? "validation.phone.found" : "ok"
            );
        }
        return null;
    }
    private RegistrationResponse registerWorkerValidation(WorkerRegistrationRequest workerRegistrationRequest){
        User validationUsername = userRepository.findByUsername(workerRegistrationRequest.getUsername());
        User validationEmail = userRepository.findByEmail(workerRegistrationRequest.getEmail());
        User validationPhone = userRepository.findByPhone(workerRegistrationRequest.getPhone());
        if(validationEmail != null || validationUsername != null || validationPhone != null){
            return new RegistrationResponse(
                    validationUsername != null ? "validation.username.found" : "ok",
                    validationEmail != null ? "validation.email.found" : "ok",
                    "ok",
                    validationPhone != null ? "validation.phone.found" : "ok"
            );
        }
        return null;
    }
    public ResponseEntity<RegistrationResponse> createCompanyAndOwner(OwnerRegistrationRequest ownerRegistrationRequest){
        RegistrationResponse validation = registerOwnerValidation(ownerRegistrationRequest);
        if(validation == null)
        {
            Company company = new Company();
            company.setName(ownerRegistrationRequest.getCompany_name());
            company.setLocation(ownerRegistrationRequest.getLocation());
            companyRepository.save(company);
            User user = new User();
            user.setCompany(company);
            user.setRole("ROLE_OWNER");
            user.setUsername(ownerRegistrationRequest.getUsername());
            user.setEmail(ownerRegistrationRequest.getEmail());
            user.setPassword(passwordEncoder.encode(ownerRegistrationRequest.getPassword()));
            user.setFullname(ownerRegistrationRequest.getFullname());
            user.setPhone(ownerRegistrationRequest.getPhone());
            userRepository.save(user);
            return new ResponseEntity<RegistrationResponse>(
                    new RegistrationResponse("ok","ok","ok","ok"),
                    HttpStatus.CREATED
            );
        }
        return new ResponseEntity<RegistrationResponse>(validation,HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<RegistrationResponse> createWorker(WorkerRegistrationRequest workerRegistrationRequest){
        RegistrationResponse validation = registerWorkerValidation(workerRegistrationRequest);
        if(validation == null){
            Worker worker = new Worker();
            workerRepository.save(worker);
            User user = new User();
            user.setWorker(worker);
            user.setUsername(workerRegistrationRequest.getUsername());
            user.setEmail(workerRegistrationRequest.getEmail());
            user.setPassword(passwordEncoder.encode(workerRegistrationRequest.getPassword()));
            user.setFullname(workerRegistrationRequest.getFullname());
            user.setRole(workerRegistrationRequest.getRole());
            user.setPhone(workerRegistrationRequest.getPhone());
            userRepository.save(user);
            return new ResponseEntity<RegistrationResponse>(
                    new RegistrationResponse("ok","ok","ok","ok"),
                    HttpStatus.CREATED
            );
        }
        return new ResponseEntity<RegistrationResponse>(validation,HttpStatus.BAD_REQUEST);
    }
}
