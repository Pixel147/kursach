package ua.com.online.appointment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.com.online.appointment.entity.Company;
import ua.com.online.appointment.entity.User;
import ua.com.online.appointment.entity.Worker;
import ua.com.online.appointment.repository.CompanyRepository;
import ua.com.online.appointment.repository.UserRepository;
import ua.com.online.appointment.response.CompanyResponse;
import ua.com.online.appointment.response.WorkerWorkDaysResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<CompanyResponse> getCompanyAppointmentInfo(Integer id){
        Optional<Company> company = companyRepository.findById(id);
        if(company.isPresent()){
            CompanyResponse response = new CompanyResponse(company.get().getName());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<List<String>> getCompanyServices(Integer id){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if(optionalCompany.isPresent()){
            List<String>services = optionalCompany.get().getWorkers().stream()
                    .map(Worker::getService)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(services,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<List<String>> getWorkerByCompanyAndService(Integer id, String service) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()) {
            List<String> workerUsernames = optionalCompany.get().getWorkers().stream()
                    .filter(worker -> service.equals(worker.getService()))
                    .map(this::findWorkerUsernameById)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(workerUsernames, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<WorkerWorkDaysResponse> getWorkDays(Integer id, String username){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()) {
            Optional<User> optionalUser = Optional.ofNullable(userRepository.findByCompanyAndUsername(optionalCompany.get(),username));
            if(optionalUser.isPresent()){
                User user = optionalUser.get();
                Worker worker = user.getWorker();
                WorkerWorkDaysResponse workDays = new WorkerWorkDaysResponse();
                workDays.setMonday(worker.getMondayStart() != null);
                workDays.setTuesday(worker.getTuesdayStart() != null);
                workDays.setWednesday(worker.getWednesdayStart() != null);
                workDays.setThursday(worker.getThursdayStart() != null);
                workDays.setFriday(worker.getFridayStart() != null);
                workDays.setSaturday(worker.getSaturdayStart() != null);
                workDays.setSunday(worker.getSundayStart()!=null);
                return new ResponseEntity<>(workDays,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    public String findWorkerUsernameById(Worker worker){
        return userRepository.findByWorker(worker).getUsername();
    }
}
