package ua.com.online.appointment.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "company")
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String location;
    @Column
    private String description;
}
