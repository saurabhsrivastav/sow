package com.ems.sow.services.implementation;

import com.ems.sow.model.InstallDeviceParameters;
import com.ems.sow.repositories.DeviceParameterInfoRepository;
import com.ems.sow.services.DeviceParameterInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceParameterInfoServiceImplementation implements DeviceParameterInfoService {

    @Autowired
    private DeviceParameterInfoRepository repository;

    Logger logger = LoggerFactory.getLogger(DeviceParameterInfoServiceImplementation.class);

    @Override
    public List<InstallDeviceParameters> getDeviceParameters(String serialNumber, String deviceModbus) {
        return repository.findBySerialNumberAndDeviceModbus(serialNumber, deviceModbus, Sort.by(Sort.Order.asc("serialNumber")));
    }

    @Override
    public List<InstallDeviceParameters> getDeviceParametersDetailsByDeviceId(String deviceId) {
        return repository.findByDeviceId(deviceId);
    }
}
