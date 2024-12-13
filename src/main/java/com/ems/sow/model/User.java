package com.ems.sow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_details")
public class User {

    @Id
    @Column(name = "userId" , nullable = false, length=40)
    private String userId;
    @Column(name = "name", nullable = false, length = 100, columnDefinition = "varchar(100)")
    private String name;
    @Column(name = "email", unique = true, nullable = false,  length = 255,  columnDefinition = "varchar(255)")
    private String email;
    @Column(name = "password", unique = true, nullable = false,  length = 50,  columnDefinition = "varchar(50)")
    private String password;
    @Column(name = "role", nullable = false, length = 100, columnDefinition = "varchar(100)")
    private String role;
}
