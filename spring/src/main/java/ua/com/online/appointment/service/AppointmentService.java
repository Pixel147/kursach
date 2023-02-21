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
import ua.com.online.appointment.response.FreeTimeResponse;
import ua.com.online.appointment.response.WorkerAndServiceResponse;
import ua.com.online.appointment.response.WorkerWorkDaysResponse;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
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
    public List<FreeTimeResponse> getFreeTimeByDay(Integer workerId, Date day){
        Optional<Worker>worker = workerRepository.findById(workerId);
        if(worker.isPresent()){
            LocalDate date = day.toLocalDate();
            String nameDayOfWeek = date.getDayOfWeek().name();
            if(nameDayOfWeek.equals("MONDAY")){
                return getFreeTimes(worker.get().getMondayStart(), worker.get().getMondayEnd());
            }
            if(nameDayOfWeek.equals("TUESDAY")){
                return getFreeTimes(worker.get().getTuesdayStart(),worker.get().getTuesdayEnd());
            }
            if(nameDayOfWeek.equals("WEDNESDAY")){
                return getFreeTimes(worker.get().getWednesdayStart(), worker.get().getWednesdayEnd());
            }
            if(nameDayOfWeek.equals("THURSDAY")){
                return getFreeTimes(worker.get().getThursdayStart(),worker.get().getThursdayEnd());
            }
            if(nameDayOfWeek.equals("FRIDAY")){
                return getFreeTimes(worker.get().getFridayStart(),worker.get().getFridayEnd());
            }
            if(nameDayOfWeek.equals("SATURDAY")){
                return getFreeTimes(worker.get().getSaturdayStart(),worker.get().getSaturdayEnd());
            }
            if(nameDayOfWeek.equals("SUNDAY")){
                return getFreeTimes(worker.get().getSundayStart(),worker.get().getSundayEnd());
            }
        }
        return null;
    }
    public static List<FreeTimeResponse> getFreeTimes(Time start, Time end) {
        List<FreeTimeResponse> freeTimes = new ArrayList<>();
        Time currentTime = start;
        while (currentTime.before(end)) {
            freeTimes.add(new FreeTimeResponse(currentTime));
            currentTime = addOneHour(currentTime);
        }
        return freeTimes;
    }
    private static Time addOneHour(Time time) {
        long timeInMillis = time.getTime() + (60 * 60 * 1000);
        return new Time(timeInMillis);
    }
}
