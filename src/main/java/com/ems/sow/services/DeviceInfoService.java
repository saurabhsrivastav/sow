package com.ems.sow.services;

import com.ems.sow.model.DeviceDetailList;
import com.ems.sow.projection.IDeviceDetailInfoList;

import java.util.List;

public interface DeviceInfoService {

    List<DeviceDetailList> getDeviceDetail(String id);

    DeviceDetailList saveDeviceDetail(DeviceDetailList deviceDetailList);

    List<IDeviceDetailInfoList> getDeviceAndSiteDetails(String id);

    List<DeviceDetailList> getDeviceDetailsByCustomerId(String id);
}
