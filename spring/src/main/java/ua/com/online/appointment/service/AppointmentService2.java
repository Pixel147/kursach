package ua.com.online.appointment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.online.appointment.entity.Company;
import ua.com.online.appointment.entity.User;
import ua.com.online.appointment.entity.Worker;
import ua.com.online.appointment.repository.CompanyRepository;
import ua.com.online.appointment.repository.UserRepository;
import ua.com.online.appointment.repository.WorkerRepository;
import ua.com.online.appointment.response.CompanyResponse;
import ua.com.online.appointment.response.WorkerAndServiceResponse;
import ua.com.online.appointment.response.WorkerWorkDaysResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService2 {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkerRepository workerRepository;

    public CompanyResponse getCompanyAppointmentInfo(Integer id){
        Optional<Company> company = companyRepository.findById(id);
        if(company.isPresent()){
            return new CompanyResponse(company.get().getName());
        }
        return null;
    }
    public List<WorkerAndServiceResponse> getWorkersAndServices(Integer id){
        Optional<Company> company = companyRepository.findById(id);
        if(company.isPresent()){
            List<WorkerAndServiceResponse> response = new ArrayList<>();
            for(Worker worker : company.get().getWorkers()){
                User userWorker = userRepository.findByWorker(worker);
                response.add(new WorkerAndServiceResponse(worker.getId(),userWorker.getUsername(),worker.getService()));
            }
            return response;
        }
        return null;
    }
    public WorkerWorkDaysResponse getWorkDays(Integer workerId){
        Optional<Worker>worker = workerRepository.findById(workerId);
        if(worker.isPresent()){
            WorkerWorkDaysResponse workDays = new WorkerWorkDaysResponse();
            workDays.setMonday(worker.get().getMondayStart() != null);
            workDays.setTuesday(worker.get().getTuesdayStart() != null);
            workDays.setWednesday(worker.get().getWednesdayStart() != null);
            workDays.setThursday(worker.get().getThursdayStart() != null);
            workDays.setFriday(worker.get().getFridayStart() != null);
            workDays.setSaturday(worker.get().getSaturdayStart() != null);
            workDays.setSunday(worker.get().getSundayStart()!=null);
            return workDays;
        }
        return null;
    }
}
