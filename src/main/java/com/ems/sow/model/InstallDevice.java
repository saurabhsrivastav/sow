package com.ems.sow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "install_devices")
public class InstallDevice {

    @Id
    private String deviceId;

    @Column (nullable = false, length=50)
    private String serialNumber;

    @Column (nullable = false, length=50)
    private String modelNumber;

    @Column(nullable = false, length=50)
    private String rtuName;

    @Column (nullable = false, length=50)
    private String rtuCategory;

    @Column (nullable = false, length=50)
    private String deviceModbus;

    @Column(nullable = false)
    private String deviceName;

    @Column(nullable = false)
    private String deviceType;

    @Column(nullable = false, length=40)
    private String customerId;

    @Column (nullable = false, length=50)
    private String rtuId;

    @Column (nullable = false, length=20)
    private String deviceStatus;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    private List<InstallDeviceParameters> deviceParameterInfo = new ArrayList<>();

//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "id", nullable = false)
//    private InstallDeviceParameters installDeviceParameters;


}

