package com.ems.sow.repositories;

import com.ems.sow.model.InstallDevice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceParameterRepository extends JpaRepository<InstallDevice, Long> {

    List<InstallDevice> findBySerialNumber(String rtuId);

    List<InstallDevice> findByCustomerId(String customerId);

    boolean existsBySerialNumberAndDeviceModbus(String serialNumber, String deviceModbus);
}
