package com.ems.sow.entities;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer alertId;
    private String alertName;
    private String device;
    private String site;
    private Date timestamp;
    private String snooze;
    private Integer customerId;
}
