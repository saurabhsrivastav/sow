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
    @Column(name = "siteId" , nullable = false, length=40)
    private String siteId;
    @Column (name = "siteName", nullable = false, length=100)
    private String siteName;
    @Column (name = "geofenceRadius", nullable = false, length=50)
    private String geofenceRadius;
    @Column (name = "latitude", nullable = false, length=50)
    private String latitude;
    @Column (name = "longitude", nullable = false, length=50)
    private String longitude;
    @Column (name = "googleMapLocation", nullable = false, length=100)
    private String googleMapLocation;

}
