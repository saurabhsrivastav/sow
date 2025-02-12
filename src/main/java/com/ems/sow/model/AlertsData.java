package com.ems.sow.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "alerts_data")
public class AlertsData {

    @Id
    @Column(name = "alertId", nullable = false, length=40)
    private String alertId;
    @Column(name = "paramCode", nullable = false, length=50)
    private String paramCode;
    @Column (name = "alarmName", nullable = false, length=50)
    private String alarmName;
    @Column (name = "alarmValue", nullable = false, length=50)
    private String alarmValue;
    @Column (name = "timestamp", nullable = false)
    private LocalDateTime timestamp;
    @Column (name = "osd", nullable = false, length=6)
    private String osd;
    @Column(name = "mdbid" , nullable = false, length=40)
    private String mdbid;

}
