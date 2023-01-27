package ua.com.online.appointment.repository;

import java.util.*;
import ua.com.online.appointment.entity.Company;
import ua.com.online.appointment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findByEmail(String email);
    User findByPhone(String phone);
    List<User> getUsersByCompanyAndAndRole(Company company, String role);
}
