package com.ems.sow.services.implementation;

import com.ems.sow.model.RtuDetails;
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
    public List<RtuDetails> getAllDeviceDetails() {
        return repository.findAll();
    }

    @Override
    public RtuDetails saveDeviceDetails(RtuDetails deviceDetails) {
        List<RtuDetails> response = findByCustomerIdAndSerialNumber(deviceDetails.getCustomerId(), deviceDetails.getSerialNumber());

        if(!response.isEmpty()) {
            RtuDetails existingDevice = response.get(0);
            existingDevice.setStatus(true);
            return existingDevice;
        } else {
            final String deviceId = UUID.randomUUID().toString();
            deviceDetails.setRtuId(deviceId);
            return repository.save(deviceDetails);
        }
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
    public RtuDetails updateDeviceDetails(RtuDetails deviceDetails) {
        final String serialNumber = deviceDetails.getSerialNumber();
        final String siteId = deviceDetails.getSiteId();
        final boolean status = deviceDetails.isStatus();
        final List<RtuDetails> responseByDeviceName = repository.findBySerialNumber(serialNumber);
        for (RtuDetails list : responseByDeviceName) {
            list.setStatus(status);
            list.setSiteId(siteId);
            repository.save(list);
        }
        return deviceDetails;
    }

    @Override
    public List<RtuDetails> getDeviceByCustomerId(String id) {
            return repository.findAllByCustomerIdAndStatus(id, false);
    }

    @Override
    public RtuDetails uninstallDevice(RtuDetails deviceDetails) {
        final String rtuId = deviceDetails.getRtuId();
        final boolean status = deviceDetails.isStatus();
        final List<RtuDetails> responseByDeviceName = repository.findByRtuId(rtuId);
        for (RtuDetails list : responseByDeviceName) {
            list.setStatus(status);
            repository.save(list);
        }
        return deviceDetails;
    }

    @Override
    public List<RtuDetails> findDeviceByRtuId(String rtuId) {
        return repository.findByRtuId(rtuId);
    }

    /**
     * @param customerId
     * @return
     */
    @Override
    public List<RtuDetails> findSerialNumbersByCustomerId(String customerId) {
        return repository.findByCustomerId(customerId);
    }

    @Override
    public List<RtuDetails> findByCustomerIdAndSerialNumber(String customerId, String SerialNumber) {
        return repository.findByCustomerIdAndSerialNumber(customerId, SerialNumber);
    }

}
