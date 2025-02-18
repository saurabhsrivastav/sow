package com.ems.sow.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "rtu_details")
public class RtuDetails {

    @Id
    @Column(nullable = false, length=40)
    private String rtuId;

    @Column (nullable = false, length=50)
    private String modelNumber;

    @Column (nullable = false, length=50)
    private String serialNumber;

    @Column(nullable = false, length=50)
    private String rtuName;

    @Column (nullable = false, length=50)
    private String rtuCategory;

    @Column (nullable = false, length=20)
    private String rtuStatus;

    @Column(nullable = false, length=40)
    private String customerId;

    @Column(length=40)
    private String siteId;

    @Column(nullable = false)
    private boolean status;

    @Column(length=40)
    private String device;

//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "device_id")
//    private InstallDevice installDevices;

//    @PrePersist
//    public void prePersist() {
//        if (this.deviceName == null) {
//            this.deviceName = "";
//        }
//    }
}
