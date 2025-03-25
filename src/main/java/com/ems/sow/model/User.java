package com.ems.sow.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_details")
public class User {

    @Id
    @Column(name = "userId" , nullable = false, length=40)
    private String userId;
    @Column(name = "name", nullable = false, length = 100, columnDefinition = "varchar(100)")
    private String name;
    @Column(name = "userName", unique = true, nullable = false,  length = 255,  columnDefinition = "varchar(255)")
    private String userName;
    @Column(name = "password", nullable = false,  length = 50,  columnDefinition = "varchar(50)")
    private String password;
    @Column(name = "customerId", length = 40,  columnDefinition = "varchar(50)")
    private String customerId;
    @Column(name = "role", nullable = false, length = 100, columnDefinition = "varchar(100)")
    private String role;
    @Column
    @Lob
    private byte[] image;
}
