package com.ems.sow.services.implementation;

import com.ems.sow.model.ImagesStock;
import com.ems.sow.repositories.DeviceParameterImageRepository;
import com.ems.sow.services.DeviceParameterImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceParameterImageServiceImplementation implements DeviceParameterImageService {

    @Autowired
    private DeviceParameterImageRepository deviceParameterImageRepository;

    @Override
    public List<ImagesStock> getListImages(String osd, String modbus) {
        return deviceParameterImageRepository
                .findByOsdAndModbusId(osd, modbus, Sort.by(Sort.Direction.DESC, "imageTimeStamp"));
    }

    @Override
    public List<byte[]> getImage(String decodedFileUrl) {
        return List.of();
    }

}