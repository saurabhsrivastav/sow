package com.ems.sow.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "device_details")
public class DeviceList {

    @Id
    @Column(nullable = false, length=40)
    private String deviceId;
    @Column(nullable = false, length=50)
    private String deviceName;
    @Column (nullable = false, length=50)
    private String model;
    @Column (nullable = false, length=50)
    private String serialNumber;
    @Column (nullable = false, length=50)
    private String deviceType;
    @Column (nullable = false, length=6)
    private boolean status;
    @Column (nullable = false, length=20)
    private String deviceStatus;
    @Column(nullable = false, length=40)
    private String customerId;
    @Column(nullable = false, length=40)
    private String siteId;

    @PrePersist
    public void prePersist() {
        status = true;
        deviceStatus = "online";
    }
}
