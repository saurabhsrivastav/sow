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
        return repository.findDeviceDetailsByCustomerId(id);
    }

    @Override
    public List<IDeviceListProj> findDeviceStatus(String id) {
        return repository.findDeviceStatus(id);
    }

    @Override
    public DeviceList updateDeviceDetails(DeviceList deviceDetails) {
        final String deviceName = deviceDetails.getDeviceName();
        final String siteId = deviceDetails.getSiteId();
        final boolean status = deviceDetails.isStatus();
        final List<DeviceList> responseByDeviceName = repository.findByDeviceName(deviceName);
        for (DeviceList list : responseByDeviceName) {
            list.setStatus(status);
            list.setSiteId(siteId);
            repository.save(list);
        }
        return deviceDetails;
    }

    @Override
    public List<DeviceList> getDeviceByCustomerId(String id) {
            return repository.findAllByCustomerIdAndStatus(id, false);
    }

    /**
     * @param deviceDetails
     * @return
     */
    @Override
    public DeviceList uninstallDevice(DeviceList deviceDetails) {
        final String deviceId = deviceDetails.getDeviceId();
        final boolean status = deviceDetails.isStatus();
        final List<DeviceList> responseByDeviceName = repository.findByDeviceId(deviceId);
        for (DeviceList list : responseByDeviceName) {
            list.setStatus(status);
            repository.save(list);
        }
        return deviceDetails;
    }

    /**
     * @param deviceId
     * @return
     */
    @Override
    public List<DeviceList> findDeviceByDeviceId(String deviceId) {
        return repository.findByDeviceId(deviceId);
    }
}
