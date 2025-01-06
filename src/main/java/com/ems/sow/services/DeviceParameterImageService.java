package com.ems.sow.services;

import com.ems.sow.model.ImagesStock;

import java.io.IOException;
import java.util.List;

public interface DeviceParameterImageService {

    List<ImagesStock> getListImages(String osd, String modbusId);

//    List getImage(String s3Key, int width, int height) throws IOException;
}
