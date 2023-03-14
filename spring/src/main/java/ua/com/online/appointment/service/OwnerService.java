package ua.com.online.appointment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ua.com.online.appointment.DTO.WorkerDTO;
import ua.com.online.appointment.entity.Company;
import ua.com.online.appointment.entity.User;
import ua.com.online.appointment.entity.Worker;
import ua.com.online.appointment.repository.CompanyRepository;
import ua.com.online.appointment.repository.UserRepository;
import ua.com.online.appointment.repository.WorkerRepository;
import ua.com.online.appointment.request.WorkerScheduleRequest;
import ua.com.online.appointment.response.OwnerInfoResponse;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OwnerService {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private WorkerRepository workerRepository;

    public OwnerInfoResponse getOwnerInfo(Integer userId, ServletRequest servletRequest){
        User user = jwtService.getUserByToken(servletRequest);
        OwnerInfoResponse ownerInfoResponse = new OwnerInfoResponse();
        if(user != null && Objects.equals(user.getId(), userId)){
            //TODO: как переписать метод чтоб он проверял и сразу возвращал для неудачной проверки?
            ownerInfoResponse.setFullname(user.getFullname());
            ownerInfoResponse.setPhone(user.getPhone());
            ownerInfoResponse.setCompanyName(user.getCompany().getName());
            ownerInfoResponse.setLocation(user.getCompany().getLocation());
            ownerInfoResponse.setDescription(user.getCompany().getDescription());
            ownerInfoResponse.setUsername(user.getUsername());
            return ownerInfoResponse;
        }
        return null;
    }
    public HttpStatus updateCompanyDescription(Integer userId, ServletRequest servletRequest, String description){
        User user = jwtService.getUserByToken(servletRequest);
        if(user != null && Objects.equals(user.getId(), userId)){
            Company company = user.getCompany();
            company.setDescription(description);
            companyRepository.save(company);
            return HttpStatus.ACCEPTED;
        }
        return  HttpStatus.BAD_REQUEST;
    }
    public List<WorkerDTO> getWorkers(Integer userId, ServletRequest servletRequest){
        User user = jwtService.getUserByToken(servletRequest);
        if(user != null && Objects.equals(user.getId(), userId)){
            List<User> userList = userRepository.getUsersByCompanyAndRole(user.getCompany(),"ROLE_WORKER");
            List<WorkerDTO>workerDTOS = new ArrayList<>();
            for (User u:userList) {
                workerDTOS.add(new WorkerDTO(u.getId(),u.getUsername(),u.getEmail(),u.getPhone(),u.getFullname(), u.getWorker().getService()));
            }
            return workerDTOS;
        }
        return null;
    }
    public HttpStatus updateWorkerSchedule(Integer userId, ServletRequest servletRequest, WorkerScheduleRequest workerScheduleRequest)
    {
        User user = jwtService.getUserByToken(servletRequest);
        if(user != null && Objects.equals(user.getId(), userId)){
            Worker worker = userRepository.findById(userId).get().getWorker();
            worker.setMondayStart(workerScheduleRequest.getMondayStart());
            worker.setMondayEnd(workerScheduleRequest.getMondayEnd());
            worker.setTuesdayStart(workerScheduleRequest.getTuesdayStart());
            worker.setTuesdayEnd(workerScheduleRequest.getTuesdayEnd());
            worker.setWednesdayStart(workerScheduleRequest.getWednesdayStart());
            worker.setWednesdayEnd(workerScheduleRequest.getWednesdayEnd());
            worker.setThursdayStart(workerScheduleRequest.getThursdayStart());
            worker.setThursdayEnd(workerScheduleRequest.getThursdayEnd());
            worker.setFridayStart(workerScheduleRequest.getFridayStart());
            worker.setFridayEnd(workerScheduleRequest.getFridayEnd());
            worker.setSaturdayStart(workerScheduleRequest.getSaturdayStart());
            worker.setSaturdayEnd(workerScheduleRequest.getSaturdayEnd());
            worker.setSundayStart(workerScheduleRequest.getSundayStart());
            worker.setSundayEnd(workerScheduleRequest.getSundayEnd());
            workerRepository.save(worker);
            return HttpStatus.ACCEPTED;
        }
        return HttpStatus.BAD_REQUEST;
    }
    public HttpStatus deleteWorker(Integer workerId){
        Optional<User> user = userRepository.findById(workerId);
        if(user.isPresent()){
            Worker worker = user.get().getWorker();
            userRepository.delete(user.get());
            workerRepository.delete(worker);
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }
}
