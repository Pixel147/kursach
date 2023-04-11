package ua.com.online.appointment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;
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
    private List<Appointment> appointments;
    @Column
    private String service;
    @Column
    private LocalTime mondayStart;
    @Column
    private LocalTime mondayEnd;
    @Column
    private LocalTime tuesdayStart;
    @Column
    private LocalTime tuesdayEnd;
    @Column
    private LocalTime wednesdayStart;
    @Column
    private LocalTime wednesdayEnd;
    @Column
    private LocalTime thursdayStart;
    @Column
    private LocalTime thursdayEnd;
    @Column
    private LocalTime fridayStart;
    @Column
    private LocalTime fridayEnd;
    @Column
    private LocalTime saturdayStart;
    @Column
    private LocalTime saturdayEnd;
    @Column
    private LocalTime sundayStart;
    @Column
    private LocalTime sundayEnd;
}
