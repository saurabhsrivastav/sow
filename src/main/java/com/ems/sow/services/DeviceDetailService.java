package com.ems.sow.services;

import com.ems.sow.model.RtuDetails;
import com.ems.sow.projection.IDeviceDetailList;
import com.ems.sow.projection.IDeviceListProj;

import java.util.List;
import java.util.Optional;

public interface DeviceDetailService {


    List<RtuDetails> getAllDeviceDetails();

    RtuDetails saveDeviceDetails(RtuDetails deviceDetails);

    Optional<List<IDeviceDetailList>> getDevices(String id);

    List<IDeviceListProj> findDeviceStatus(String id);

    RtuDetails updateDeviceDetails(RtuDetails deviceDetails);

    List<RtuDetails> getDeviceByCustomerId(String id);

    RtuDetails uninstallDevice(RtuDetails deviceDetails);

    List<RtuDetails> findDeviceByRtuId(String rtuId);

    List<RtuDetails> findSerialNumbersByCustomerId(String customerId);

}
