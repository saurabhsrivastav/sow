package com.ems.sow.model;

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
    @Column(name = "customerId" , nullable = false, length=40)
    private String customerId;
    @Column (name = "customerName", nullable = false, length=50)
    private String customerName;
    @Column (name = "address", nullable = false, length=255)
    private String address;
    @Column (name = "contactPerson", nullable = false, length=100)
    private String contactPerson;
    @Column (name = "subStartDate", nullable = false)
    private String subStartDate;
    @Column (name = "subEndDate", nullable = false)
    private String subEndDate;
    @Column (name = "siteCapping", nullable = false, length=10)
    private String siteCapping;
    @Column (name = "deviceCapping", nullable = false, length=10)
    private String deviceCapping;
    @Transient
    @Column (name = "userAdded", nullable = false, length=10)
    private String userAdded;
   /* @Column (name = "image", nullable = false)
    private byte[] image;*/
    @Column (name = "applicationId", nullable = false, length=40)
    private String applicationId;
}
