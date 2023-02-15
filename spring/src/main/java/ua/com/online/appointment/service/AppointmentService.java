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
import ua.com.online.appointment.repository.WorkerRepository;
import ua.com.online.appointment.response.AppointmentResponse;
import ua.com.online.appointment.response.WorkerWorkDays;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkerRepository workerRepository;

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
                List<String>workerUsernames = new ArrayList<>();
                List<Worker>workers = company.getWorkers();
                for(int i = 0; i < workers.size(); i++){
                    if(service.equals(workers.get(i).getService())){
                        workerUsernames.add(findWorkerUsernameById(workers.get(i)));
                    }
                }
                if(workerUsernames != null){
                    return new ResponseEntity(workerUsernames,HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    public ResponseEntity getWorkDaysWorkerByCompanyAndUsername(int id,String username){
        if(id >= 1){
            Company company = companyRepository.findById(id);
            if(company != null){
                User user = userRepository.findByUsername(username);
                if(user != null){
                    Worker worker = user.getWorker();
                    WorkerWorkDays workDays = new WorkerWorkDays();
                    if(worker.getMondayStart() != null){
                        workDays.setMonday(true);
                    }
                    if(worker.getTuesdayStart() != null){
                        workDays.setTuesday(true);
                    }
                    if(worker.getWednesdayStart() != null){
                        workDays.setWednesday(true);
                    }
                    if(worker.getThursdayStart() != null){
                        workDays.setThursday(true);
                    }
                    if(worker.getFridayStart() != null){
                        workDays.setFriday(true);
                    }
                    if(worker.getSaturdayStart() != null){
                        workDays.setSaturday(true);
                    }
                    if(worker.getSundayStart()!=null){
                        workDays.setSunday(true);
                    }
                    return new ResponseEntity(workDays,HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    public String findWorkerUsernameById(Worker worker){
        return userRepository.findByWorker(worker).getUsername();
    }
}
