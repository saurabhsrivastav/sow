package com.ems.sow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String parameterCode;
    private String parameterName;
    private String unit;
    private Integer thresholdMin;
    private Integer thresholdMax;
    private Integer delay;
    private String serialNumber;
    private String deviceModbus;

    @Column(name = "device_id")
    private String deviceId;
}

