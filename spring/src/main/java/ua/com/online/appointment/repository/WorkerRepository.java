package ua.com.online.appointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.online.appointment.entity.Worker;

public interface WorkerRepository extends JpaRepository<Worker,Integer> {
}
