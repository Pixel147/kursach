package ua.com.online.appointment.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "id_company")
    @ManyToOne
    private Company company;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String fullname;
    @Column
    private String phone;
    @Column
    private String role;
    @OneToOne
    @JoinColumn(name = "id_worker")
    private Worker worker;
}
