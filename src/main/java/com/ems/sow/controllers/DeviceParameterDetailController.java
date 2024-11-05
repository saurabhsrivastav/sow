package com.ems.sow.controllers;

import com.ems.sow.model.StreamData;
import com.ems.sow.services.DeviceParameterDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/device-parameters-detail")
public class DeviceParameterDetailController {

    @Autowired
    DeviceParameterDetailService deviceParameterDetailService;

    @GetMapping("/{serialNumber}/{deviceModbus}")
    public ResponseEntity<?> getDeviceDetails( @PathVariable String serialNumber,
                                               @PathVariable String deviceModbus) {

        final List<StreamData> deviceDetailLists = deviceParameterDetailService.getDeviceParameterDetails(serialNumber, deviceModbus);
        return ResponseEntity.ok(deviceDetailLists);
    }
}
