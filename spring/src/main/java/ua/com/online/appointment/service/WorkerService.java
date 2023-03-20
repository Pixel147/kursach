package ua.com.online.appointment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ua.com.online.appointment.entity.Appointment;
import ua.com.online.appointment.entity.User;
import ua.com.online.appointment.entity.Worker;
import ua.com.online.appointment.repository.AppointmentRepository;
import ua.com.online.appointment.repository.UserRepository;
import ua.com.online.appointment.response.UserAppointmentResponse;
import ua.com.online.appointment.response.WorkerInfoResponse;
import ua.com.online.appointment.response.WorkerJobAppointments;

import javax.servlet.ServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class WorkerService {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private UserRepository userRepository;

    public WorkerInfoResponse getWorkerInfo(ServletRequest servletRequest){
        User user = jwtService.getUserByToken(servletRequest);
        if(user == null){
            return null;
        }
        WorkerInfoResponse response = new WorkerInfoResponse();
        Worker worker = user.getWorker();
        response.setUsername(user.getUsername());
        response.setFullname(user.getFullname());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setWorkStartsIn(findMinimumStartWorkTime(worker).toString());
        response.setWorkEndsIn(findMaxEndWorkTime(worker).toString());
        response.setMondayFlag(worker.getMondayStart() == null ? 1 : null);
        response.setTuesdayFlag(worker.getTuesdayStart() == null ? 2 : null);
        response.setWednesdayFlag(worker.getWednesdayStart() == null ? 3 : null);
        response.setThursdayFlag(worker.getThursdayStart() == null ? 4 : null);
        response.setFridayFlag(worker.getFridayStart() == null ? 5 : null);
        response.setSaturdayFlag(worker.getSaturdayStart() == null ? 6 : null);
        response.setSundayFlag(worker.getSundayStart() == null ? 0 : null);
        return response;
    }
    public List<UserAppointmentResponse> getAppointments(ServletRequest servletRequest){
        User user = jwtService.getUserByToken(servletRequest);
        if(user == null){
            return null;
        }
        List<UserAppointmentResponse> response = new ArrayList<>();
        List<Appointment> appointments = appointmentRepository.getAppointmentsByClientAndStatus(user, "booked");
        if(appointments != null){
            for(Appointment app : appointments){
                UserAppointmentResponse element = new UserAppointmentResponse();
                element.setId(app.getId());
                element.setCompanyName(app.getWorker().getCompany().getName());
                element.setWorkerFullName(userRepository.findByWorker(app.getWorker()).getFullname());
                element.setService(app.getService());
                element.setWorkerPhone(userRepository.findByWorker(app.getWorker()).getPhone());
                element.setDate(app.getTimeStart().toLocalDate());
                element.setTime(app.getTimeStart().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")));
                response.add(element);
            }
        }
        return response;
    }
    public List<WorkerJobAppointments> getJobAppointments(ServletRequest servletRequest, LocalDate date){
        User user = jwtService.getUserByToken(servletRequest);
        if(user == null){
            return null;
        }
        Worker worker = user.getWorker();
        return retrieveAppointmentsByDay(worker,date);
    }
    public List<WorkerJobAppointments> retrieveAppointmentsByDay(Worker worker, LocalDate date){
        List<Appointment> appointments = appointmentRepository.getAppointmentsByWorkerAndStatusAndTimeStartBetween(
                worker,
                "booked",
                LocalDateTime.of(date, LocalTime.MIN),
                LocalDateTime.of(date.plusDays(6),LocalTime.MAX)
        );
        List<WorkerJobAppointments> response = new ArrayList<>();
        for(Appointment app : appointments){
            WorkerJobAppointments jobAppointment = new WorkerJobAppointments();
            jobAppointment.setId(app.getId());
            jobAppointment.setService(app.getService());
            jobAppointment.setClientName(app.getClient().getFullname());
            jobAppointment.setDateStart(app.getTimeStart().toLocalDate().toString());
            jobAppointment.setTimeStart(app.getTimeStart().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")));
            jobAppointment.setDateEnd(app.getTimeEnd().toLocalDate().toString());
            jobAppointment.setTimeEnd(app.getTimeEnd().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")));
            jobAppointment.setClientPhone(app.getClient().getPhone());
            response.add(jobAppointment);
        }
        return response;
    }
    public HttpStatus cancelAppointment(Integer idAppointment,String status, ServletRequest servletRequest){
        User user = jwtService.getUserByToken(servletRequest);
        if(user == null || idAppointment == null){
            return HttpStatus.BAD_REQUEST;
        }
        Optional<Appointment> appointment = appointmentRepository.findById(idAppointment);
        Appointment app = appointment.get();
        app.setStatus(status);
        appointmentRepository.save(app);
        return HttpStatus.OK;
    }
    public LocalTime findMinimumStartWorkTime(Worker worker) {
        LocalTime[] allTimes = new LocalTime[] {
                worker.getMondayStart(),
                worker.getTuesdayStart(),
                worker.getWednesdayStart(),
                worker.getThursdayStart(),
                worker.getFridayStart(),
                worker.getSaturdayStart(),
                worker.getSundayStart()
        };
        return Arrays.stream(allTimes)
                .filter(Objects::nonNull)
                .min(LocalTime::compareTo)
                .orElse(null);
    }
    public LocalTime findMaxEndWorkTime(Worker worker) {
        LocalTime[] allTimes = new LocalTime[] {
                worker.getMondayEnd(),
                worker.getTuesdayEnd(),
                worker.getWednesdayEnd(),
                worker.getThursdayEnd(),
                worker.getFridayEnd(),
                worker.getSaturdayEnd(),
                worker.getSundayEnd()
        };
        return Arrays.stream(allTimes)
                .filter(Objects::nonNull)
                .max(LocalTime::compareTo)
                .orElse(null);
    }
}
