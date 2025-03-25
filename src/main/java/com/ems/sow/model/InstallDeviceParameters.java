package com.ems.sow.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "install_device_parameters")
public class InstallDeviceParameters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int delay;
    private String deviceModbus;
    private String parameterCode;
    private String parameterName;
    @Column(name = "serial_number")
    private String serialNumber;
    private int thresholdMax;
    private int thresholdMin;
    private String unit;

    @ManyToOne
    @JoinColumn(name = "device_id", referencedColumnName = "device_id", nullable = false)
    @JsonBackReference
    private InstallDevice device;

}
