package com.ems.sow.controllers;

import com.ems.sow.model.InstallDeviceParameters;
import com.ems.sow.services.DeviceParameterInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/device-parameters-info")
public class DeviceParameterInfoController {

    @Autowired
    DeviceParameterInfoService deviceParameterInfoService;

    @GetMapping("/{serialNumber}/{deviceModbus}")
    public ResponseEntity<List<InstallDeviceParameters>> getDeviceParameters(@PathVariable String serialNumber,
                                                                                             @PathVariable String deviceModbus) {
        final List<InstallDeviceParameters> deviceDetailLists = deviceParameterInfoService.getDeviceParameters(serialNumber, deviceModbus);
        return ResponseEntity.ok(deviceDetailLists);
    }
}
