package com.ems.sow.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "site_details")
public class SiteDetails {
    @Id
    private String siteId;
    @Column (nullable = false, length=100)
    private String siteName;
    @Column (nullable = false, length=100)
    private String siteAddress;
    @Column (nullable = false, length=5)
    private Integer geofenceRadius;
    @Column (nullable = false)
    private String latitude;
    @Column (nullable = false)
    private String longitude;
    @Column (nullable = false, length=20)
    private String location;
    @Column (nullable = false, length=100)
    private String selectedSiteId;
    @Column(nullable = false, length=40)
    private String customerId;

}
