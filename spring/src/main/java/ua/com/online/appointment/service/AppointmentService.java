package ua.com.online.appointment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.online.appointment.entity.*;
import ua.com.online.appointment.repository.AppointmentRepository;
import ua.com.online.appointment.repository.CompanyRepository;
import ua.com.online.appointment.repository.UserRepository;
import ua.com.online.appointment.repository.WorkerRepository;
import ua.com.online.appointment.request.AppointmentRequest;
import ua.com.online.appointment.response.CompanyResponse;
import ua.com.online.appointment.response.FreeTimeResponse;
import ua.com.online.appointment.response.WorkerResponse;
import ua.com.online.appointment.response.WorkerWorkDaysResponse;

import javax.servlet.ServletRequest;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AppointmentRepository appointmentRepository;

    public CompanyResponse getCompanyInfo(Integer companyId){
        Optional<Company> company = companyRepository.findById(companyId);
        return company.map(value -> new CompanyResponse(value.getName())).orElse(null);
    }
    public List<WorkerResponse> getWorkers(Integer companyId){
        Optional<Company> company = companyRepository.findById(companyId);
        if(!company.isPresent()){
            return null;
        }
        List<WorkerResponse> response = new ArrayList<>();
        for(Worker worker : company.get().getWorkers()){
            User userWorker = userRepository.findByWorker(worker);
            response.add(new WorkerResponse(worker.getId(),userWorker.getFullname(),worker.getService()));
        }
        return response;

    }
    public WorkerWorkDaysResponse getWorkDays(Integer workerId){
        Optional<Worker>worker = workerRepository.findById(workerId);
        if(!worker.isPresent()){
            return null;
        }
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
    public List<FreeTimeResponse> getFreeTimeByDay(Integer workerId, Date day){
        Optional<Worker>worker = workerRepository.findById(workerId);
        if(!worker.isPresent()){
            return null;
        }
        LocalDate date = day.toLocalDate();
        DayOfWeek myDayOfWeek = date.getDayOfWeek();
        List<FreeTimeResponse> response = new ArrayList<>();
        if(myDayOfWeek == DayOfWeek.MONDAY){
            response = getFreeTimes(worker.get().getMondayStart(), worker.get().getMondayEnd());
        }
        if(myDayOfWeek == DayOfWeek.TUESDAY){
            response = getFreeTimes(worker.get().getTuesdayStart(),worker.get().getTuesdayEnd());
        }
        if(myDayOfWeek == DayOfWeek.WEDNESDAY){
            response = getFreeTimes(worker.get().getWednesdayStart(), worker.get().getWednesdayEnd());
        }
        if(myDayOfWeek == DayOfWeek.THURSDAY){
            response = getFreeTimes(worker.get().getThursdayStart(),worker.get().getThursdayEnd());
        }
        if(myDayOfWeek == DayOfWeek.FRIDAY){
            response = getFreeTimes(worker.get().getFridayStart(),worker.get().getFridayEnd());
        }
        if(myDayOfWeek == DayOfWeek.SATURDAY){
            response = getFreeTimes(worker.get().getSaturdayStart(),worker.get().getSaturdayEnd());
        }
        if(myDayOfWeek == DayOfWeek.SUNDAY){
            response = getFreeTimes(worker.get().getSundayStart(),worker.get().getSundayEnd());
        }
        response = deleteExcitingTime(response, date, worker.get());
        return response;
    }
    public Boolean createAppointment(ServletRequest servletRequest, AppointmentRequest request){
        User user = jwtService.getUserByToken(servletRequest);
        Optional<Worker> worker = workerRepository.findById(request.getWorkerId());
        if(!worker.isPresent() && user == null){
            return false;
        }
        LocalDateTime timeStart = LocalDateTime.of(request.getDate(),request.getTime());
        LocalDateTime timEnd = LocalDateTime.of(request.getDate(),request.getTime().plusHours(1));
        Appointment appointment = new Appointment();
        appointment.setWorker(worker.get());
        appointment.setService(worker.get().getService());
        appointment.setTimeStart(timeStart);
        appointment.setTimeEnd(timEnd);
        appointment.setClient(user);
        appointment.setStatus("booked");
        appointmentRepository.save(appointment);
        return true;
    }
    public List<FreeTimeResponse> deleteExcitingTime(List<FreeTimeResponse>response, LocalDate date, Worker worker){
        List<Appointment> excitingAppointments = appointmentRepository.getAppointmentsByWorkerAndTimeStartBetween(
                worker,
                LocalDateTime.of(date, LocalTime.MIN),
                LocalDateTime.of(date,LocalTime.MAX));
        for(int i = 0; i < response.size(); i++){
            for(Appointment exAp : excitingAppointments){
                if(response.get(i).getTime() == exAp.getTimeStart().toLocalTime()){
                    response.remove(i);
                }
            }
        }
        return response;
    }
    public static List<FreeTimeResponse> getFreeTimes(LocalTime start, LocalTime end) {
        List<FreeTimeResponse> freeTimes = new ArrayList<>();
        LocalTime currentTime = start;
        while (currentTime.isBefore(end)) {
            freeTimes.add(new FreeTimeResponse(currentTime));
            currentTime = currentTime.plusHours(1);
        }
        return freeTimes;
    }
}
