package com.ems.sow.repositories;

import com.ems.sow.model.InstallDeviceParameters;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DeviceParameterInfoRepository extends JpaRepository<InstallDeviceParameters, String> {

    List<InstallDeviceParameters> findBySerialNumberAndDeviceModbus(String serialNumber, String deviceModbus, Sort sort);

    List<InstallDeviceParameters> findByDevice_DeviceId(String deviceId);

    Optional<InstallDeviceParameters> findBySerialNumberAndParameterCode(String serialNumber, String parameterCode);

}
