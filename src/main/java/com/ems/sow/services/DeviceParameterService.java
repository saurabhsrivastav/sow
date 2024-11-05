package com.ems.sow.services;

import com.ems.sow.model.InstallDevice;

import java.util.List;

public interface DeviceParameterService {

    InstallDevice saveParameters(InstallDevice device);

    List<InstallDevice> getDeviceDetails(String deviceId);
}
