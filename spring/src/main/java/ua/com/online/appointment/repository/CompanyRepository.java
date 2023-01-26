package ua.com.online.appointment.repository;

import ua.com.online.appointment.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Integer> {
    Company findByName(String name);
}
