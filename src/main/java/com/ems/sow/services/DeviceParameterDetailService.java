package com.ems.sow.services;

import com.ems.sow.model.StreamData;

import java.util.List;

public interface DeviceParameterDetailService {

    List<StreamData> getDeviceParameterDetails(String id, String deviceModbus);
}
