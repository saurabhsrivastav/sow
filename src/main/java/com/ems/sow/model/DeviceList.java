package com.ems.sow.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    @Column (nullable = false, length=50)
    private String modelNumber;
    @Column (nullable = false, length=50)
    private String serialNumber;
    @Column(nullable = false, length=50)
    private String deviceName;
    @Column (nullable = false, length=50)
    private String deviceCategory;
    @Column (nullable = false, length=20)
    private String deviceStatus;
    @Column(nullable = false, length=40)
    private String customerId;
    @Column(nullable = false)
    private boolean status;
    //@Column (length=3)
    //private Integer deviceParameter;
/*
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="device_list_fk", referencedColumnName = "deviceId")
    List<DeviceParameter> deviceParameter = new ArrayList<>();
*/

}
