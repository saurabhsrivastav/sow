package com.ems.sow.services;

import com.ems.sow.model.InstallDeviceParameters;

import java.util.List;

public interface DeviceParameterInfoService {

    List<InstallDeviceParameters> getDeviceParameters(String serialNumber, String deviceModbus);

    List<InstallDeviceParameters> getDeviceParametersDetailsByDeviceId(String deviceId);
}
