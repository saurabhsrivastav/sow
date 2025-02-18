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
@Table(name = "install_device_parameters")
@Entity
public class InstallDeviceParameters {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    private String parameterCode;

    @Column(nullable = false, length = 50)
    private String parameterName;

    @Column(nullable = false, length = 10)
    private String unit;

    @Column(nullable = false)
    private Integer thresholdMin;

    @Column(nullable = false)
    private Integer thresholdMax;

    @Column(nullable = false)
    private Integer delay;

    @Column(nullable = false, length = 50)
    private String serialNumber;

    @Column(nullable = false, length = 50)
    private String deviceModbus;

    @Column(name = "device_id")
    private String deviceId;

//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "devices_id")
//    private InstallDevice installDevices;
}

