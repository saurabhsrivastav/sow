package com.ems.sow.controllers;

import com.ems.sow.model.InstallDevice;
import com.ems.sow.services.DeviceParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/device-parameters")
public class DeviceParameterController {

    @Autowired
    DeviceParameterService deviceParameterService;

    @PostMapping (value="/add")
    public ResponseEntity<InstallDevice> saveDeviceDetails(@RequestBody InstallDevice device) {
        final InstallDevice deviceDetailLists = deviceParameterService.saveParameters(device);
        return ResponseEntity.ok(deviceDetailLists);
    }

    @GetMapping("/{serialNumber}")
    public ResponseEntity<List<InstallDevice>> getDeviceDetails(@PathVariable String serialNumber) {
        final List<InstallDevice> deviceDetailLists = deviceParameterService.getDeviceDetails(serialNumber);
        return ResponseEntity.ok(deviceDetailLists);
    }
}
