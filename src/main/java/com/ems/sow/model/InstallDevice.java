package com.ems.sow.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(name = "install_devices", uniqueConstraints = @UniqueConstraint(columnNames = "serial_number"))
@Table(name = "install_devices")
public class InstallDevice {

    @Id
    @Column(name = "device_id", nullable = false, unique = true)
    private String deviceId;  // Ensure deviceId is the primary key

    private String customerId;
    private String deviceModbus;
    private String deviceName;
    private String deviceStatus;
    private String deviceType;
    private String modelNumber;
    private String rtuCategory;
    private String rtuId;
    private String rtuName;

//    @Column(name = "serial_number", unique = true, nullable = false)
    @Column(name = "serial_number", nullable = false)
    private String serialNumber;

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<InstallDeviceParameters> installDeviceParameters = new ArrayList<>();

}
