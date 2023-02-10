package ua.com.online.appointment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

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
    @OneToMany
    @JsonManagedReference
    private List<Appointment> appointment;
    @Column
    private int id_schedule;
    @Column
    private String service;
    @Column
    private Timestamp mondayStart;
    @Column
    private Timestamp mondayEnd;
    @Column
    private Timestamp tuesdayStart;
    @Column
    private Timestamp tuesdayEnd;
    @Column
    private Timestamp wednesdayStart;
    @Column
    private Timestamp wednesdayEnd;
    @Column
    private Timestamp thursdayStart;
    @Column
    private Timestamp thursdayEnd;
    @Column
    private Timestamp fridayStart;
    @Column
    private Timestamp fridayEnd;
    @Column
    private Timestamp saturdayStart;
    @Column
    private Timestamp saturdayEnd;
    @Column
    private Timestamp sundayStart;
    @Column
    private Timestamp sundayEnd;
    @Column
    private boolean mondayWorkSwitch;
    @Column
    private boolean tuesdayWorkSwitch;
    @Column
    private boolean wednesdayWorkSwitch;
    @Column
    private boolean thursdayWorkSwitch;
    @Column
    private boolean fridayWorkSwitch;
    @Column
    private boolean saturdayWorkSwitch;
    @Column
    private boolean sundayWorkSwitch;
}
