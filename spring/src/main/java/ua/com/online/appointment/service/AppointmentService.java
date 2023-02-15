package ua.com.online.appointment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.com.online.appointment.entity.Company;
import ua.com.online.appointment.entity.Worker;
import ua.com.online.appointment.repository.CompanyRepository;
import ua.com.online.appointment.repository.UserRepository;
import ua.com.online.appointment.response.AppointmentResponse;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity getCompanyAppointmentInfo(int id){
        if(id >= 1){
            Company company = companyRepository.findById(id);
            if(company != null){
                AppointmentResponse response = new AppointmentResponse(companyRepository.findById(id).getName());
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    public ResponseEntity getCompanyServices(int id){
        if(id >= 1){
            Company company = companyRepository.findById(id);
            if(company != null){
                List<String>services = new ArrayList<>();
                List<Worker>workers = company.getWorkers();
                for(int i = 0; i < company.getWorkers().size(); i++){
                    services.add(workers.get(i).getService());
                }
                if(services != null){
                    return new ResponseEntity(services,HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    public ResponseEntity getWorkerByCompanyAndService(int id, String service){
        if(id >= 1){
            Company company = companyRepository.findById(id);
            if(company != null){
                List<String>workerNames = new ArrayList<>();
                List<Worker>workers = company.getWorkers();
                for(int i = 0; i < workers.size(); i++){
                    if(service.equals(workers.get(i).getService())){
                        workerNames.add(findWorkerUsernameById(workers.get(i)));
                    }
                }
                if(workerNames != null){
                    return new ResponseEntity(workerNames,HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    public String findWorkerUsernameById(Worker worker){
        return userRepository.findByWorker(worker).getUsername();
    }
}
