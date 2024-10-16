package com.ems.sow.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "site_details")
public class SiteDetails {
    @Id
    private String siteId;
    @Column (nullable = false, length=50)
    private String selectedSiteId;
    @Column (nullable = false, length=50)
    private String siteName;
    @Column (nullable = false, length=100)
    private String siteAddress;
    @Column (nullable = false, length=7)
    private String geofenceRadius;
    @Column (nullable = false)
    private String latitude;
    @Column (nullable = false)
    private String longitude;
    @Column (nullable = false, length=50)
    private String location;
    @Column
    @Lob
    private byte[] image;
    @Column(nullable = false, length=40)
    private String customerId;
}
