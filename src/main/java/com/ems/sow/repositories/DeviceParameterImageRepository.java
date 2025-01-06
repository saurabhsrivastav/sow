package com.ems.sow.repositories;

import com.ems.sow.model.ImagesStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceParameterImageRepository extends JpaRepository<ImagesStock, String> {

    List<ImagesStock> findByOsdAndModbusId(String osd, String modbus);
}
