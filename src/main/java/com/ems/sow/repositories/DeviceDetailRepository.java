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


    @Query (value = "SELECT DISTINCT \n" +
            "    de.device_id AS deviceId, \n" +
            "    de.device_name AS deviceName, \n" +
            "    de.model_number AS modelNumber, \n" +
            "    de.serial_number AS serialNumber, \n" +
            "    de.device_category AS deviceCategory, \n" +
            "    de.customer_id AS customerId, \n" +
            "    de.status AS status, \n" +
            "    de.device_status AS deviceStatus, \n" +
            "    STRING_AGG(DISTINCT al.alert_name, ', ') AS alert, \n" +
            "    (SELECT se.site_name \n" +
            "     FROM site_details se \n" +
            "     WHERE se.site_id = de.site_id) AS siteName\n" +
            "FROM \n" +
            "    device_details de\n" +
            "LEFT JOIN \n" +
            "    alert_lists al ON al.customer_id = de.customer_id\n" +
            "WHERE \n" +
            "    de.customer_id = ?1\n" +
            "GROUP BY \n" +
            "    de.device_id, de.device_name, de.model_number, de.serial_number, \n" +
            "    de.device_category, de.customer_id, de.device_status", nativeQuery = true)
    Optional<List<IDeviceDetailList>> findDeviceDetailsByCustomerId(String id);

    @Query(value = "SELECT \n" +
            "    COUNT(*) AS all_devices,\n" +
            "    SUM(CASE WHEN device_status = 'online' THEN 1 ELSE 0 END) AS online,\n" +
            "    SUM(CASE WHEN device_status = 'offline' THEN 1 ELSE 0 END) AS offline,\n" +
            "    SUM(CASE WHEN device_status = 'powerfailure' THEN 1 ELSE 0 END) AS powerfailure\n" +
            "FROM \n" +
            "    device_details dd\n" +
            "WHERE \n" +
            "    dd.customer_id = ?1", nativeQuery = true)
    List<IDeviceListProj> findDeviceStatus(String id);

    List<DeviceList> findByDeviceName(String deviceId);

    List<DeviceList> findAllByCustomerIdAndStatus(String id, boolean status);

    List<DeviceList> findByDeviceId(String deviceId);
}
