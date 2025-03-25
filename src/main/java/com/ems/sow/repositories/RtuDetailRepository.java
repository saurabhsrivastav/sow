package com.ems.sow.repositories;

import com.ems.sow.model.RtuDetails;
import com.ems.sow.projection.IRtuDetailList;
import com.ems.sow.projection.IRtuListProj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RtuDetailRepository extends JpaRepository<RtuDetails, String> {

    @Query(value = """
            WITH latest_stream_data AS (
                SELECT 
                    osd AS serial_number, 
                    event_timestamp,
                    ROW_NUMBER() OVER (PARTITION BY osd ORDER BY event_timestamp DESC) AS rn
                FROM stream_data
            )
            SELECT  
                de.rtu_id AS rtuId, 
                de.rtu_name AS rtuName, 
                de.model_number AS modelNumber, 
                de.serial_number AS serialNumber, 
                de.rtu_category AS rtuCategory, 
                de.customer_id AS customerId, 
                de.status AS status, 
                de.rtu_status AS deviceStatus, 
                se.site_name AS siteName,
                ls.event_timestamp
            FROM rtu_details de
            LEFT JOIN latest_stream_data ls 
                ON de.serial_number = ls.serial_number AND ls.rn = 1
            LEFT JOIN site_details se 
                ON de.site_id = se.site_id
            WHERE de.customer_id = ?1
            """, nativeQuery = true)
    Optional<List<IRtuDetailList>> findRtuDetailsByCustomerId(String id);


    @Query(value = "SELECT \n" +
            "    COUNT(*) AS all_devices,\n" +
            "    SUM(CASE WHEN rtu_status = 'online' THEN 1 ELSE 0 END) AS online,\n" +
            "    SUM(CASE WHEN rtu_status = 'offline' THEN 1 ELSE 0 END) AS offline,\n" +
            "    SUM(CASE WHEN rtu_status = 'powerfailure' THEN 1 ELSE 0 END) AS powerfailure\n" +
            "FROM \n" +
            "    rtu_details dd\n" +
            "WHERE \n" +
            "    dd.customer_id = ?1", nativeQuery = true)
    List<IRtuListProj> findRtuStatus(String id);

    List<RtuDetails> findBySerialNumber(String serialNumber);

    List<RtuDetails> findAllByCustomerIdAndStatus(String id, boolean status);

    List<RtuDetails> findByRtuId(String rtuId);

    List<RtuDetails> findByCustomerId(String customerId);

    List<RtuDetails> findByCustomerIdAndSerialNumber(String customerId, String serialNumber);

}
