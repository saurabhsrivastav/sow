package com.ems.sow.repositories;

import com.ems.sow.model.StreamData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceParameterDetailRepository extends JpaRepository<StreamData, String> {

    List<StreamData> findByOsdAndMdbid(String serialNumber, String deviceModbus);
}
