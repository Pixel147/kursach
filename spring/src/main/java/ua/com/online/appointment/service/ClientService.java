package ua.com.online.appointment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.online.appointment.entity.Appointment;
import ua.com.online.appointment.entity.User;
import ua.com.online.appointment.repository.AppointmentRepository;
import ua.com.online.appointment.repository.UserRepository;
import ua.com.online.appointment.response.UserAppointmentResponse;
import ua.com.online.appointment.response.ClientInfoResponse;

import javax.servlet.ServletRequest;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;

    public ClientInfoResponse getUser(ServletRequest servletRequest){
        User user = jwtService.getUserByToken(servletRequest);
        if(user == null){
            return null;
        }
        ClientInfoResponse response = new ClientInfoResponse();
        response.setUsername(user.getUsername());
        response.setFullname(user.getFullname());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
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
    public void deleteAppointment(Integer idAppointment,ServletRequest servletRequest){
        User user = jwtService.getUserByToken(servletRequest);
        if(user != null && idAppointment != null){
            Optional<Appointment> appointment = appointmentRepository.findById(idAppointment);
            appointmentRepository.delete(appointment.get());
        }
    }
}
