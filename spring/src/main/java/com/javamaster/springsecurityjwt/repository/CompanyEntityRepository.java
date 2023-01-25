package com.javamaster.springsecurityjwt.repository;

import com.javamaster.springsecurityjwt.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyEntityRepository extends JpaRepository<CompanyEntity,Integer> {
    CompanyEntity findByName(String name);
}
