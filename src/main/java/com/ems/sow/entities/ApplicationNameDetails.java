package com.ems.sow.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "application_name_details")
public class ApplicationNameDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer applicationId;
    private String applicationName;

}
