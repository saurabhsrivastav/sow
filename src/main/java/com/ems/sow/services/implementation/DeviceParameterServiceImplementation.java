package com.ems.sow.services.implementation;

import com.ems.sow.model.InstallDevice;
import com.ems.sow.repositories.DeviceParameterRepository;
import com.ems.sow.services.DeviceParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DeviceParameterServiceImplementation implements DeviceParameterService {

    @Autowired
    private DeviceParameterRepository repository;

    public InstallDevice saveParameters(InstallDevice devices) {

        if (repository.
                existsBySerialNumberAndDeviceModbus(devices.getSerialNumber(), devices.getDeviceModbus())) {
            return null;
        }
        devices.setDeviceId(UUID.randomUUID().toString());
        return repository.save(devices);
    }

    @Override
    public List<InstallDevice> getDeviceDetails(String rtuId) {
        return repository.findBySerialNumber(rtuId);
    }

    @Override
    public List<InstallDevice> getDeviceDetailsbyCustID(String customerId) {
        return repository.findByCustomerId(customerId);
    }
}
