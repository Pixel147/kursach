package ua.com.online.appointment.service;

import ua.com.online.appointment.config.jwt.JwtProvider;
import ua.com.online.appointment.entity.Company;
import ua.com.online.appointment.entity.User;
import ua.com.online.appointment.entity.Worker;
import ua.com.online.appointment.response.AuthResponse;
import ua.com.online.appointment.repository.CompanyRepository;
import ua.com.online.appointment.repository.UserRepository;
import ua.com.online.appointment.repository.WorkerRepository;
import ua.com.online.appointment.request.AuthRequest;
import ua.com.online.appointment.request.OwnerRegistrationRequest;
import ua.com.online.appointment.request.WorkerRegistrationRequest;
import ua.com.online.appointment.response.RegistrationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;

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
    @Autowired
    private JwtService jwtService;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserService userService;
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
    public RegistrationResponse createCompanyAndOwner(OwnerRegistrationRequest ownerRegistrationRequest){
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
            return null;
        }
        return validation;
    }
    public RegistrationResponse createWorker(WorkerRegistrationRequest workerRegistrationRequest, ServletRequest servletRequest){
        User owner = jwtService.getUserByToken(servletRequest);
        RegistrationResponse validation = registerWorkerValidation(workerRegistrationRequest);
        if(owner != null && validation == null){
            Worker worker = new Worker();
            worker.setCompany(owner.getCompany());
            workerRepository.save(worker);
            User user = new User();
            user.setWorker(worker);
            user.setCompany(owner.getCompany());
            user.setUsername(workerRegistrationRequest.getUsername());
            user.setEmail(workerRegistrationRequest.getEmail());
            user.setPassword(passwordEncoder.encode(workerRegistrationRequest.getPassword()));
            user.setFullname(workerRegistrationRequest.getFullname());
            user.setRole(workerRegistrationRequest.getRole());
            user.setPhone(workerRegistrationRequest.getPhone());
            userRepository.save(user);
            return null;
        }
        return validation;
    }
    public AuthResponse login(AuthRequest request){
        User user = userService.findByLoginAndPassword(request.getUsername(), request.getPassword());
        if(user != null){
            String token = jwtProvider.generateToken(user.getUsername());
            return new AuthResponse(user.getId(),user.getRole(),token);
        }
        return null;
    }
}
