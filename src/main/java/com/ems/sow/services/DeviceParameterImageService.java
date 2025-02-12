package com.ems.sow.services;

import com.ems.sow.model.ImagesStock;

import java.io.IOException;
import java.util.List;

public interface DeviceParameterImageService {

    List<ImagesStock> getListImages(String osd, String modbusId);


    List<byte[]> getImage(String decodedFileUrl);
}
