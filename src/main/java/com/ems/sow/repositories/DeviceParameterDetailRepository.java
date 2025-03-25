package com.ems.sow.repositories;

import com.ems.sow.model.StreamData;
import com.ems.sow.projection.StreamDataProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDateTime;
import java.util.List;

public interface DeviceParameterDetailRepository extends JpaRepository<StreamData, String> {

    @Query(
            value = "SELECT " +
                    "    sd.obj_id, " +
                    "    sd.osd, " +
                    "    sd.event_timestamp, " +
                    "    sd.mdbid, " +
                    "    sd.model, " +
                    "    jsonb_object_agg(" +
                    "        idp.parameter_name, " +
                    "        COALESCE(jt.value, 'N/A') ORDER BY idp.id DESC " +
                    "    ) AS updated_tele_data " +
                    "FROM stream_data sd " +
                    "JOIN install_device_parameters idp " +
                    "    ON sd.osd = idp.serial_number " +
                    "    AND sd.mdbid = idp.device_modbus " +
                    "LEFT JOIN LATERAL jsonb_each_text(sd.tele_data) AS jt(key, value) " +
                    "    ON jt.key = idp.parameter_code " +
                    "WHERE sd.osd = :osd " +
                    "AND sd.mdbid = :mdbid " +
                    "GROUP BY sd.obj_id, sd.osd, sd.event_timestamp, sd.mdbid, sd.model " +
                    "ORDER BY sd.event_timestamp DESC",
            countQuery = "SELECT COUNT(*) FROM stream_data sd " +
                    "JOIN install_device_parameters idp " +
                    "ON sd.tele_data->>idp.parameter_code IS NOT NULL " +
                    "AND sd.osd = idp.serial_number " +
                    "AND sd.mdbid = idp.device_modbus " +
                    "WHERE sd.osd = :osd AND sd.mdbid = :mdbid",
            nativeQuery = true
    )
    Page<StreamDataProjection> findByOsdValue(
            @Param("osd") String osd,
            @Param("mdbid") String mdbid,
            Pageable pageable
    );



    @Query(
            value = "SELECT " +
                    "    sd.obj_id, " +
                    "    sd.osd, " +
                    "    sd.event_timestamp, " +
                    "    sd.mdbid, " +
                    "    sd.model, " +
                    "    jsonb_object_agg(" +
                    "        idp.parameter_name, " +
                    "        COALESCE(jt.value, 'N/A') ORDER BY idp.id DESC " +
                    "    ) AS updated_tele_data " +
                    "FROM stream_data sd " +
                    "JOIN install_device_parameters idp " +
                    "    ON sd.osd = idp.serial_number " +
                    "    AND sd.mdbid = idp.device_modbus " +
                    "LEFT JOIN LATERAL jsonb_each_text(sd.tele_data) AS jt(key, value) " +
                    "    ON jt.key = idp.parameter_code " +
                    "WHERE sd.osd = :osd " +
                    "AND sd.mdbid = :mdbid " +
                    "AND sd.event_timestamp BETWEEN :startDate AND :endDate " +
                    "GROUP BY sd.obj_id, sd.osd, sd.event_timestamp, sd.mdbid, sd.model " +
                    "ORDER BY sd.event_timestamp DESC",
            nativeQuery = true
    )
    List<StreamDataProjection> findFilteredData(@Param("osd") String osd,
                                                @Param("mdbid") String mdbid,
                                                @Param("startDate") LocalDateTime startDate,
                                                @Param("endDate") LocalDateTime endDate);


}



