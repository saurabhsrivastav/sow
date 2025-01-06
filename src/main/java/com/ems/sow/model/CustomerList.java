package com.ems.sow.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customer_details")
public class CustomerList {
    @Id
    @Column(nullable = false, length=40)
    private String customerId;
    @Column (nullable = false, length=50)
    private String customerName;
    @Column (nullable = false, length=255)
    private String address;
    @Column (nullable = false, length=50)
    private String contactPerson;
    @Column (nullable = false)
    private String subStartDate;
    @Column (nullable = false)
    private String subEndDate;
    @Column
    @Lob
    private byte[] image;

    @Column (nullable = false, length=4)
    private String deviceCapping;
    @Column (nullable = false, length=4)
    private String siteCapping;

    private String applicationId;
    private String userName;
}
