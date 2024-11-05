package com.ems.sow.services.implementation;

import com.ems.sow.model.StreamData;
import com.ems.sow.repositories.DeviceParameterDetailRepository;
import com.ems.sow.services.DeviceParameterDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceParameterDetailServiceImplementation implements DeviceParameterDetailService {

    @Autowired
    private DeviceParameterDetailRepository repository;

    @Override
    public List<StreamData> getDeviceParameterDetails(String serialNumber, String deviceModbus) {
        return repository.findByOsdAndMdbid(serialNumber, deviceModbus);
    }
}
