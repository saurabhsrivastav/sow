package com.ems.sow.services;

import com.ems.sow.dto.InstallDeviceDTO;
import com.ems.sow.model.InstallDevice;

import java.util.List;

public interface DeviceParameterService {

    InstallDevice saveParameters(InstallDeviceDTO device);

    List<InstallDevice> getDeviceDetails(String deviceId);

    List<InstallDevice> getDeviceDetailsbyCustID(String customerId);

    boolean findBySerialNumberAndDeviceModbus(String serialNumber, String deviceModbus);
}
