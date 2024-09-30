package com.ems.sow.repositories;

import com.ems.sow.model.DeviceDetailList;
import com.ems.sow.projection.IDeviceDetailInfoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceInfoRepository extends JpaRepository<DeviceDetailList, String> {

    @Query(value = "SELECT DISTINCT sd.site_name AS siteName, dd.device_name AS deviceName\n" +
            "FROM device_details dd\n" +
            "JOIN site_details sd\n" +
            "ON dd.customer_id = sd.customer_id\n" +
            "WHERE dd.customer_id = ?1\n" +
            "AND dd.status = true", nativeQuery = true)
    List<IDeviceDetailInfoList> findByDeviceAndSite(String id);

    List<DeviceDetailList> findByCustomerId(String id);

    List<DeviceDetailList> findDistinctByDeviceId(String id);
}
