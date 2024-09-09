package com.ems.sow.services.implementation;

import com.ems.sow.model.DeviceDetailList;
import com.ems.sow.repositories.DeviceInfoRepository;
import com.ems.sow.services.DeviceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DeviceInfoServiceImplementation implements DeviceInfoService {

    @Autowired
    private DeviceInfoRepository infoRepository;

    @Override
    public List<DeviceDetailList> getDeviceDetail(String id) {
        return infoRepository.findByDeviceId(id);
    }

    /**
     * @param deviceDetailList
     * @return
     */
    @Override
    public DeviceDetailList saveDeviceDetail(DeviceDetailList deviceDetailList) {
        final String uuid = UUID.randomUUID().toString();
        deviceDetailList.setDeviceListId(uuid);
        return infoRepository.save(deviceDetailList);
    }
}
