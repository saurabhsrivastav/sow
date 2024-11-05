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
        String id = UUID.randomUUID().toString();
        devices.setDeviceId(id);
        return repository.save(devices);
    }

    @Override
    public List<InstallDevice> getDeviceDetails(String rtuId) {
        return repository.findBySerialNumber(rtuId);
    }
}
