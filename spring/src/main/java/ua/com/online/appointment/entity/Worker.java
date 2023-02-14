package ua.com.online.appointment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Data
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_company")
    @JsonBackReference
    private Company company;
    @Column
    private String service;
    @Column
    private Time mondayStart;
    @Column
    private Time mondayEnd;
    @Column
    private Time tuesdayStart;
    @Column
    private Time tuesdayEnd;
    @Column
    private Time wednesdayStart;
    @Column
    private Time wednesdayEnd;
    @Column
    private Time thursdayStart;
    @Column
    private Time thursdayEnd;
    @Column
    private Time fridayStart;
    @Column
    private Time fridayEnd;
    @Column
    private Time saturdayStart;
    @Column
    private Time saturdayEnd;
    @Column
    private Time sundayStart;
    @Column
    private Time sundayEnd;
}
