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

    @Query(value = "SELECT * FROM alerts_data " +
            "WHERE osd = :osd AND mdbid = :mdbid " +
            "ORDER BY timestamp DESC", nativeQuery = true)
    List<AlertsData> findByOsdAndMdbid(@Param("osd") String osd, @Param("mdbid") String mdbid);

}
