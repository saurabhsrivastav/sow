package com.ems.sow.services.implementation;

import com.ems.sow.model.DeviceDetailList;
import com.ems.sow.projection.IDeviceDetailInfoList;
import com.ems.sow.repositories.DeviceInfoRepository;
import com.ems.sow.services.DeviceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DeviceInfoServiceImplementation implements DeviceInfoService {

    @Autowired
    private DeviceInfoRepository deviceInfoRepository;

    @Override
    public List<DeviceDetailList> getDeviceDetail(String id) {
        return deviceInfoRepository.findDistinctByDeviceId(id);
    }

    @Override
    public DeviceDetailList saveDeviceDetail(DeviceDetailList deviceDetailList) {
        final String uuid = UUID.randomUUID().toString();
        deviceDetailList.setDeviceListId(uuid);
        return deviceInfoRepository.save(deviceDetailList);
    }

    @Override
    public List<IDeviceDetailInfoList> getDeviceAndSiteDetails(String id) {
        return deviceInfoRepository.findByDeviceAndSite(id);
    }

    @Override
    public List<DeviceDetailList> getDeviceDetailsByCustomerId(String id) {
        return deviceInfoRepository.findByCustomerId(id);
    }

}
