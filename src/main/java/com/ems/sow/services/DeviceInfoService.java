package com.ems.sow.services;

import com.ems.sow.model.DeviceDetailList;

import java.util.List;

public interface DeviceInfoService {

    List<DeviceDetailList> getDeviceDetail(String id);

    DeviceDetailList saveDeviceDetail(DeviceDetailList deviceDetailList);
}
