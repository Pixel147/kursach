package com.javamaster.springsecurityjwt.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class WorkerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_company")
    private CompanyEntity companyEntity;
    @Column
    private int id_schedule;
    @Column
    private String service;
}
