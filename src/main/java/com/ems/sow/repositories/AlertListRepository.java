package com.ems.sow.repositories;

import com.ems.sow.model.AlertsData;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertListRepository extends JpaRepository<AlertsData, String> {

//    @Query(value="SELECT alert_id, param_code, alarm_name, alarm_value, timestamp, osd, mdbid\n" +
//            "FROM alerts_data \n" +
//            "WHERE osd = ?1 AND mdbid = ?2\n" +
//            "ORDER BY timestamp DESC", nativeQuery = true)
//    List<AlertsData> findByOsdAndMdbid(@Param("osd") String osd, @Param("mdbid") String mdbid);

    @Query(value = "SELECT * FROM alerts_data " +
            "WHERE osd = :osd AND mdbid = :mdbid " +
            "ORDER BY timestamp DESC", nativeQuery = true)
    List<AlertsData> findByOsdAndMdbid(@Param("osd") String osd, @Param("mdbid") String mdbid);

   // List<AlertsData> findByOsdAndMdbid(String osd, String mdbid, Sort sort);
}
