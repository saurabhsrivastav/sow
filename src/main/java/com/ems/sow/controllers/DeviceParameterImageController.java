package com.ems.sow.controllers;

import com.ems.sow.model.ImagesStock;
import com.ems.sow.services.DeviceParameterImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/device-parameters-image-detail")
public class DeviceParameterImageController {

    @Autowired
    private DeviceParameterImageService parameterImageService;

    @GetMapping("/{osd}/{modbusId}")
    public List<ImagesStock> getImages(@PathVariable String osd,
                                       @PathVariable String modbusId) {

         return parameterImageService.getListImages(osd, modbusId);

    }


//    @GetMapping("/{fileUrl}")
//    public List<byte[]> getImages(
//            @PathVariable String fileUrl
//    ) {
//
//        final List<ImagesStock> image = parameterImageService.getImage(fileUrl);
//
//        return image;
//
//    }
}
