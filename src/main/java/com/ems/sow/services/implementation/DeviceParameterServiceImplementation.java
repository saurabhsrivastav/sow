package com.ems.sow.services.implementation;

import com.ems.sow.dto.InstallDeviceDTO;
import com.ems.sow.model.InstallDevice;
import com.ems.sow.model.InstallDeviceParameters;
import com.ems.sow.repositories.DeviceParameterInfoRepository;
import com.ems.sow.repositories.DeviceParameterRepository;
import com.ems.sow.services.DeviceParameterService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeviceParameterServiceImplementation implements DeviceParameterService {

    private final DeviceParameterRepository repository;

    public DeviceParameterServiceImplementation(DeviceParameterRepository repository) {
        this.repository = repository;
    }

    @Override
    public InstallDevice saveParameters(InstallDeviceDTO deviceDTO) {
        if (deviceDTO == null) {
            return null;
        }

        boolean exists = repository.existsBySerialNumberAndDeviceModbus(deviceDTO.getSerialNumber(), deviceDTO.getDeviceModbus());

        if (exists) {
            return null; // Device already exists
        }

        InstallDevice device = new InstallDevice();
        device.setDeviceId(UUID.randomUUID().toString());
        device.setCustomerId(deviceDTO.getCustomerId());
        device.setDeviceModbus(deviceDTO.getDeviceModbus());
        device.setDeviceName(deviceDTO.getDeviceName());
        device.setDeviceStatus(deviceDTO.getDeviceStatus());
        device.setDeviceType(deviceDTO.getDeviceType());
        device.setModelNumber(deviceDTO.getModelNumber());
        device.setRtuCategory(deviceDTO.getRtuCategory());
        device.setRtuId(deviceDTO.getRtuId());
        device.setRtuName(deviceDTO.getRtuName());
        device.setSerialNumber(deviceDTO.getSerialNumber());

        deviceDTO.setDeviceId(UUID.randomUUID().toString());

        // Convert device parameters
        List<InstallDeviceParameters> updatedParams = Optional.ofNullable(deviceDTO.getDeviceParameterInfo())
                .orElse(Collections.emptyList())
                .stream()
                .map(paramDTO -> {
                    InstallDeviceParameters param = new InstallDeviceParameters();
                    param.setId(null);
                    param.setDelay(paramDTO.getDelay());
                    param.setDeviceModbus(paramDTO.getDeviceModbus());
                    param.setParameterCode(paramDTO.getParameterCode());
                    param.setParameterName(paramDTO.getParameterName());
                    param.setSerialNumber(paramDTO.getSerialNumber());
                    param.setThresholdMax(paramDTO.getThresholdMax());
                    param.setThresholdMin(paramDTO.getThresholdMin());
                    param.setUnit(paramDTO.getUnit());
                    param.setDevice(device); // Link parameter to the parent device
                    return param;
                })
                .collect(Collectors.toList());

        // Set parameters in the parent device entity
        device.setInstallDeviceParameters(updatedParams);

        // Save device along with parameters
        return repository.save(device);
    }


    @Override
    public List<InstallDevice> getDeviceDetails(String serialNumber) {
        return repository.findBySerialNumber(serialNumber);
    }

    @Override
    public List<InstallDevice> getDeviceDetailsbyCustID(String customerId) {
        return repository.findByCustomerId(customerId);
    }

    @Override
    public boolean findBySerialNumberAndDeviceModbus(String serialNumber, String deviceModbus) {
        return repository.existsBySerialNumberAndDeviceModbus(serialNumber, deviceModbus);
    }
}