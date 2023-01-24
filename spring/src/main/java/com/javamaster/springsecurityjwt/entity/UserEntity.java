package com.javamaster.springsecurityjwt.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "id_company")
    @ManyToOne
    private CompanyEntity companyEntity;
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
}
