package ua.com.online.appointment.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_company")
    private Company company;
    @Column
    private int id_schedule;
    @Column
    private String service;
}
