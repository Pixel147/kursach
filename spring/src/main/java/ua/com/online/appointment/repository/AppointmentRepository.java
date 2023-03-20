package ua.com.online.appointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.online.appointment.entity.Appointment;
import ua.com.online.appointment.entity.User;
import ua.com.online.appointment.entity.Worker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> getAppointmentsByWorkerAndTimeStartBetween(Worker worker, LocalDateTime queryTimeStart, LocalDateTime queryTimeEnd);
    List<Appointment> getAppointmentsByClientAndStatus(User user, String status);
    List<Appointment> getAppointmentsByWorkerAndStatusAndTimeStartBetween(Worker worker, String status, LocalDateTime queryTimeStart, LocalDateTime queryTimeEnd);
}