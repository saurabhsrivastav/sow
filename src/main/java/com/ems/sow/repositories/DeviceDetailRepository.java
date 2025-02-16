package com.ems.sow.repositories;

import com.ems.sow.model.RtuDetails;
import com.ems.sow.projection.IDeviceDetailList;
import com.ems.sow.projection.IDeviceListProj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceDetailRepository extends JpaRepository<RtuDetails, String> {


    @Query (value = "SELECT DISTINCT de.rtu_id AS rtuId, de.rtu_name AS rtuName, de.model_number AS modelNumber, de.serial_number AS serialNumber, de.rtu_category AS rtuCategory, \n" +
            "de.customer_id AS customerId, de.status AS status, de.device_status AS deviceStatus, \n" +
            "(SELECT se.site_name \n" +
            " FROM site_details se \n" +
            " WHERE se.site_id = de.site_id) AS siteName\n" +
            " FROM rtu_details de \n" +
            " WHERE \n" +
            " de.customer_id = ?1\n" +
            " GROUP BY \n" +
            " de.rtu_id, de.rtu_name, de.model_number, de.serial_number, \n" +
            " de.rtu_category, de.customer_id, de.device_status", nativeQuery = true)
    Optional<List<IDeviceDetailList>> findDeviceDetailsByCustomerId(String id);

    @Query(value = "SELECT \n" +
            "    COUNT(*) AS all_devices,\n" +
            "    SUM(CASE WHEN device_status = 'online' THEN 1 ELSE 0 END) AS online,\n" +
            "    SUM(CASE WHEN device_status = 'offline' THEN 1 ELSE 0 END) AS offline,\n" +
            "    SUM(CASE WHEN device_status = 'powerfailure' THEN 1 ELSE 0 END) AS powerfailure\n" +
            "FROM \n" +
            "    rtu_details dd\n" +
            "WHERE \n" +
            "    dd.customer_id = ?1", nativeQuery = true)
    List<IDeviceListProj> findDeviceStatus(String id);

    List<RtuDetails> findBySerialNumber(String serialNumber);

    List<RtuDetails> findAllByCustomerIdAndStatus(String id, boolean status);

    List<RtuDetails> findByRtuId(String deviceId);

    List<RtuDetails> findByCustomerId(String customerId);

    List<RtuDetails>findByCustomerIdAndSerialNumber(String customerId, String serialNumber);

}
