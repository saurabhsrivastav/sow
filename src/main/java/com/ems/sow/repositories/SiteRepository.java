package com.ems.sow.repositories;

import com.ems.sow.model.SiteDetails;
import com.ems.sow.projection.ISiteDetailsProj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiteRepository extends JpaRepository<SiteDetails, String> {

    List<SiteDetails> findByCustomerId(String id);


    @Query(value = "SELECT si.site_name as siteName, \n" +
            "       si.site_address as siteAddress, \n" +
            "       si.geofence_radius as geofenceRadius, \n" +
            "       si.location as location, \n" +
            "       dd.rtu_name as rtuName\n" +
            "       FROM site_details si \n" +
            "       JOIN rtu_details dd ON si.site_id = dd.site_id\n" +
            "       WHERE si.site_id = ?1\n" +
            "       ORDER BY rtuName ASC", nativeQuery = true)
    List<ISiteDetailsProj> findBySiteId(String siteId);

    boolean existsBySiteName(String siteName);
}
