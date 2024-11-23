package com.ems.sow.repositories;

import com.ems.sow.model.InstallDeviceParameters;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceParameterInfoRepository extends JpaRepository<InstallDeviceParameters, String> {

    List<InstallDeviceParameters> findBySerialNumberAndDeviceModbus(String serialNumber, String deviceModbus, Sort sort);
}
