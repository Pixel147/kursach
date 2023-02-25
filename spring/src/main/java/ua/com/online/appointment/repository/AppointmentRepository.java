package ua.com.online.appointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.online.appointment.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
}