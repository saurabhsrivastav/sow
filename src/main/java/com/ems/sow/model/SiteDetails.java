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
    @Column(nullable = false, length=40)
    private String siteId;
    @Column (nullable = false, length=100)
    private String siteName;
    @Column (nullable = false, length=5)
    private String geofenceRadius;
    @Column (nullable = false, length=10)
    private String latitude;
    @Column (nullable = false, length=10)
    private String longitude;
    @Column (nullable = false, length=20)
    private String googleMapLocation;
    @Column (nullable = false, length=40)
    private String selectedSiteId;
    @Column(nullable = false, length=40)
    private String customerId;

    @PrePersist
    public void prePersist() {
        if (this.selectedSiteId == null) {
            this.selectedSiteId = "0";
        }
    }


}
