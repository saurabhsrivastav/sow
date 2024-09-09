package com.ems.sow.services.implementation;

import com.ems.sow.model.DeviceList;
import com.ems.sow.projection.IDeviceDetailList;
import com.ems.sow.projection.IDeviceListProj;
import com.ems.sow.repositories.DeviceDetailRepository;
import com.ems.sow.services.DeviceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeviceDetailImplementation implements DeviceDetailService {

    @Autowired
    private DeviceDetailRepository repository;


    /**
     * @param id
     * @return
     */
    @Override
    public Optional<IDeviceDetailList> getDeviceDetails(String id) {
        return Optional.empty();
    }

    @Override
    public List<DeviceList> getAllDeviceDetails() {
        return repository.findAll();
    }

    @Override
    public DeviceList saveDeviceDetails(DeviceList deviceDetails) {
        final String deviceId = UUID.randomUUID().toString();
        deviceDetails.setDeviceId(deviceId);
        return repository.save(deviceDetails);
    }

    @Override
    public Optional<List<IDeviceDetailList>> getDevices(String id) {
        return repository.findByCustId(id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<IDeviceListProj> findDevice(String id) {
        return repository.findDevice(id);
    }
}
