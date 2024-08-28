package com.ems.sow.entities;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "site_details")
public class SiteDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long siteId;
    @NonNull
    private String siteName;
    @NonNull
    private String geofenceRadius;
    @NonNull
    private String latitude;
    @NonNull
    private String longitude;
    @NonNull
    private String googleMapLocation;
    @NonNull
    private String customerId;

}
