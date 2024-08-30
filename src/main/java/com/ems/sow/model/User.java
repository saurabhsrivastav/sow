package com.ems.sow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Column(name = "email", unique = true, nullable = false,  length = 100,  columnDefinition = "varchar(100)")
    private String email;
    @Column(name = "passcode",  nullable = false, length = 20, columnDefinition = "varchar(20)")
    private String passcode;
    @Column(name = "name", nullable = false, length = 100, columnDefinition = "varchar(100)")
    private String name;

}
