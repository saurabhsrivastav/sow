package com.ems.sow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "device_detail_list")
public class DeviceDetailList {

    @Id
    @Column(nullable = false, length=40)
    private String deviceListId;
    @Column(nullable = false, length=50)
    private String serialNumber;
    @Column(nullable = false, length=40)
    private String location;
    @Column(nullable = false, length=40)
    private String powerStatus;
    @Column(nullable = false, length=10)
    private String onlineStatus;
    @Column(nullable = false, length=3)
    private Integer cabinetTemperature;
    @Column(nullable = false, length=3)
    private Integer ambientTemperature;
    private Date installationDate = new Date();
    private Date manufacturingDate = new Date();
    @Column(nullable = false, length=40)
    private String deviceId;

   /* @PrePersist
    public void prePersist() {
        if (this.serialNumber == null) {
            //this.cabinetTemperature = 0;
            //this.ambientTemperature = 0;
            this.installationDate = new Date();
            this.manufacturingDate = new Date();
        }

    }*/
}
