package com.ems.sow.repositories;

import com.ems.sow.model.DeviceList;
import com.ems.sow.projection.IDeviceDetailList;
import com.ems.sow.projection.IDeviceListProj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceDetailRepository extends JpaRepository<DeviceList, String> {

    @Query (value = "SELECT \n" +
            "    de.device_id AS deviceId, \n" +
            "    de.device_name AS deviceName, \n" +
            "    de.model AS model, \n" +
            "    de.serial_number AS serialNumber, \n" +
            "    si.site_name AS siteName, \n" +
            "    de.device_status AS deviceStatus, \n" +
            "    de.status AS status,\n" +
            "    (\n" +
            "        SELECT STRING_AGG(DISTINCT al.alert_name, ', ')\n" +
            "        FROM alert_lists al\n" +
            "        WHERE al.customer_id = de.customer_id\n" +
            "    ) AS alert\n" +
            "FROM \n" +
            "    device_details de \n" +
            "JOIN \n" +
            "    site_details si ON de.customer_id = si.customer_id\n" +
            "WHERE \n" +
            "    de.customer_id = ?1", nativeQuery = true)
    Optional<List<IDeviceDetailList>> findByCustId(String id);


    List<DeviceList> findByCustomerId(String id);

    @Query(value = "SELECT \n" +
            "    COUNT(*) AS all_devices,\n" +
            "    SUM(CASE WHEN device_status = 'online' THEN 1 ELSE 0 END) AS online,\n" +
            "    SUM(CASE WHEN device_status = 'offline' THEN 1 ELSE 0 END) AS offline,\n" +
            "    SUM(CASE WHEN device_status = 'powerfailure' THEN 1 ELSE 0 END) AS powerfailure\n" +
            "FROM \n" +
            "    device_details dd\n" +
            "WHERE \n" +
            "    dd.customer_id = ?1", nativeQuery = true)
    List<IDeviceListProj> findDevice(String id);
}
