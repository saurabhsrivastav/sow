package com.ems.sow.services;

import com.ems.sow.model.DeviceList;
import com.ems.sow.projection.IDeviceDetailList;
import com.ems.sow.projection.IDeviceListProj;

import java.util.List;
import java.util.Optional;

public interface DeviceDetailService {


    List<DeviceList> getAllDeviceDetails();

    DeviceList saveDeviceDetails(DeviceList deviceDetails);

    Optional<List<IDeviceDetailList>> getDevices(String id);

    List<IDeviceListProj> findDeviceStatus(String id);

    DeviceList updateDeviceDetails(DeviceList deviceDetails);

    List<DeviceList> getDeviceByCustomerId(String id);

    DeviceList uninstallDevice(DeviceList deviceDetails);

    List<DeviceList>findDeviceByDeviceId(String deviceId);
}
