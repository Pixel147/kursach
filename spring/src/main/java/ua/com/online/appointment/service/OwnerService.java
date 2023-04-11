package ua.com.online.appointment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ua.com.online.appointment.entity.Appointment;
import ua.com.online.appointment.entity.Company;
import ua.com.online.appointment.entity.User;
import ua.com.online.appointment.entity.Worker;
import ua.com.online.appointment.repository.AppointmentRepository;
import ua.com.online.appointment.repository.CompanyRepository;
import ua.com.online.appointment.repository.UserRepository;
import ua.com.online.appointment.repository.WorkerRepository;
import ua.com.online.appointment.request.WorkerScheduleRequest;
import ua.com.online.appointment.response.AppointmentOwnerResponse;
import ua.com.online.appointment.response.OwnerInfoResponse;
import ua.com.online.appointment.response.OwnerScheduleResponse;
import ua.com.online.appointment.response.WorkerDTO;

import javax.servlet.ServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    @Autowired
    private AppointmentRepository appointmentRepository;

    public OwnerInfoResponse getOwnerInfo(Integer userId, ServletRequest servletRequest){
        User user = jwtService.getUserByToken(servletRequest);
        OwnerInfoResponse ownerInfoResponse = new OwnerInfoResponse();
        if(user != null && Objects.equals(user.getId(), userId)){
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
        Worker worker = user.get().getWorker();
        User user1 = user.get();
        userRepository.delete(user1);
        workerRepository.delete(worker);
        return HttpStatus.OK;
    }

    public List<AppointmentOwnerResponse> getAppointments(ServletRequest servletRequest)
    {
        User user = jwtService.getUserByToken(servletRequest);
        if(user == null)
        {
            return null;
        }
        List<AppointmentOwnerResponse> appointmentOwnerResponse = new ArrayList<>();
        List<Appointment> appointments = appointmentRepository.getAppointmentsByClientAndStatus(user, "booked");
        if(appointments != null)
        {
            for (Appointment app: appointments) {

                appointmentOwnerResponse.add(new AppointmentOwnerResponse(
                        app.getWorker().getCompany().getName(),
                        userRepository.findByWorker(app.getWorker()).getFullname(),
                        app.getService(),
                        userRepository.findByWorker(app.getWorker()).getPhone(),
                        app.getTimeStart().toLocalDate().toString(), app.getTimeStart().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"))
                ));
            }
        }
        return appointmentOwnerResponse;
    }

    public List<OwnerScheduleResponse> getAppointmentsByDay(ServletRequest servletRequest, LocalDate day)
    {
        User user = jwtService.getUserByToken(servletRequest);
        if(user == null)
        {
            return null;
        }

        List<OwnerScheduleResponse> ownerScheduleResponse = new ArrayList<>();
        List<Worker> workers = user.getCompany().getWorkers();
        if(workers == null)
        {
            return null;
        }

        for (Worker worker : workers) {
            List<Appointment> appointments = appointmentRepository.getAppointmentsByWorkerAndStatusAndTimeStartBetween(worker, "booked", LocalDateTime.of(day, LocalTime.MIN), LocalDateTime.of(day, LocalTime.MAX));
            for (Appointment app : appointments) {
                ownerScheduleResponse.add(new OwnerScheduleResponse(
                        app.getWorker().getCompany().getName(),
                        userRepository.findByWorker(app.getWorker()).getFullname(),
                        app.getService(),
                        userRepository.findByWorker(app.getWorker()).getPhone(),
                        app.getTimeStart().toLocalDate().toString(), app.getTimeStart().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")), app.getClient().getFullname(), app.getClient().getPhone()

                ));
            }
        }
        return ownerScheduleResponse;
    }
}
