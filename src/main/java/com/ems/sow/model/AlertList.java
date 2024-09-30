package com.ems.sow.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "alert_lists")
public class AlertList {

    @Id
    @Column(name = "alertId", nullable = false, length=40)
    private String alertId;
    @Column(name = "alertName", nullable = false, length=50)
    private String alertName;
    @Column (name = "deviceId", nullable = false, length=50)
    private String deviceId;
    @Column (name = "site", nullable = false, length=50)
    private String site;
    @Column (name = "timestamp", nullable = false)
    private Date timestamp;
    @Column (name = "snooze", nullable = false, length=6)
    private Boolean snooze;
    @Column(name = "customerId" , nullable = false, length=40)
    private String customerId;

}
