package com.ems.sow.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customer_details")
public class CustomerList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer customerId;
    private String customerName;
    private String address;
    private String contactPerson;
    private Date subStartDate;
    private Date subEndDate;
    private String siteCapping;
    private String deviceCapping;
    private String userAdded;
    private Integer applicationId;
}
