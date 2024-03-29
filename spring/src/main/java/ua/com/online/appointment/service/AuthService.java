package ua.com.online.appointment.service;

import ua.com.online.appointment.config.jwt.JwtProvider;
import ua.com.online.appointment.entity.Company;
import ua.com.online.appointment.entity.User;
import ua.com.online.appointment.entity.Worker;
import ua.com.online.appointment.request.ClientRegistrationRequest;
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
import java.time.LocalTime;

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
    private RegistrationResponse registerClientValidation(ClientRegistrationRequest request){
        User validationUsername = userRepository.findByUsername(request.getUsername());
        User validationEmail = userRepository.findByEmail(request.getEmail());
        User validationPhone = userRepository.findByPhone(request.getPhone());
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
            worker.setService(workerRegistrationRequest.getService());
            if(workerRegistrationRequest.getMondayStart() != -1 && workerRegistrationRequest.getMondayEnd() != -1)
            {
                worker.setMondayStart(LocalTime.of(workerRegistrationRequest.getMondayStart(),0));
                worker.setMondayEnd(LocalTime.of(workerRegistrationRequest.getMondayEnd(),0));
            }

            if(workerRegistrationRequest.getTuesdayStart() != -1 && workerRegistrationRequest.getTuesdayEnd() != -1)
            {
                worker.setTuesdayStart(LocalTime.of(workerRegistrationRequest.getTuesdayStart(),0));
                worker.setTuesdayEnd(LocalTime.of(workerRegistrationRequest.getTuesdayEnd(),0));
            }

            if(workerRegistrationRequest.getWednesdayStart() != -1 && workerRegistrationRequest.getWednesdayEnd() != -1)
            {
                worker.setWednesdayStart(LocalTime.of(workerRegistrationRequest.getWednesdayStart(),0));
                worker.setWednesdayEnd(LocalTime.of(workerRegistrationRequest.getWednesdayEnd(),0));
            }

            if(workerRegistrationRequest.getThursdayStart() != -1 && workerRegistrationRequest.getThursdayEnd() != -1)
            {
                worker.setThursdayStart(LocalTime.of(workerRegistrationRequest.getThursdayStart(),0));
                worker.setThursdayEnd(LocalTime.of(workerRegistrationRequest.getThursdayEnd(),0));
            }

            if(workerRegistrationRequest.getFridayStart() != -1 && workerRegistrationRequest.getFridayEnd() != -1)
            {
                worker.setFridayStart(LocalTime.of(workerRegistrationRequest.getFridayStart(),0));
                worker.setFridayEnd(LocalTime.of(workerRegistrationRequest.getFridayEnd(),0));
            }

            if(workerRegistrationRequest.getSaturdayStart() != -1 && workerRegistrationRequest.getSaturdayEnd() != -1)
            {
                worker.setSaturdayStart(LocalTime.of(workerRegistrationRequest.getSaturdayStart(),0));
                worker.setSaturdayEnd(LocalTime.of(workerRegistrationRequest.getSaturdayEnd(),0));
            }

            if(workerRegistrationRequest.getSundayStart() != -1 && workerRegistrationRequest.getSundayEnd() != -1)
            {
                worker.setSundayStart(LocalTime.of(workerRegistrationRequest.getSundayStart(),0));
                worker.setSundayEnd(LocalTime.of(workerRegistrationRequest.getSundayEnd(),0));
            }

            workerRepository.save(worker);
            User user = new User();
            user.setWorker(worker);
            user.setCompany(owner.getCompany());
            user.setUsername(workerRegistrationRequest.getUsername());
            user.setEmail(workerRegistrationRequest.getEmail());
            user.setPassword(passwordEncoder.encode(workerRegistrationRequest.getPassword()));
            user.setFullname(workerRegistrationRequest.getFullname());
            user.setRole("ROLE_WORKER");
            user.setPhone(workerRegistrationRequest.getPhone());
            userRepository.save(user);
            return null;
        }
        return validation;
    }
    public RegistrationResponse createClient(ClientRegistrationRequest request){
        RegistrationResponse validation = registerClientValidation(request);
        if(validation != null){
            return validation;
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());
        user.setFullname(request.getFullname());
        user.setRole("ROLE_CLIENT");
        userRepository.save(user);
        return null;
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
