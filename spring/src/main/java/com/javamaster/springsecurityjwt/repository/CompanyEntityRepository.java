package com.javamaster.springsecurityjwt.repository;

import com.javamaster.springsecurityjwt.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyEntityRepository extends JpaRepository<Company,Integer> {
    Company findByName(String name);
}
