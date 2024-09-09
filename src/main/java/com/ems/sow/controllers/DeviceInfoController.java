package com.ems.sow.controllers;

import com.ems.sow.model.DeviceDetailList;
import com.ems.sow.services.DeviceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/device-details-info")
public class DeviceInfoController {

    @Autowired
    private DeviceInfoService infoService;

    @GetMapping("/{id}")
    public ResponseEntity<List<DeviceDetailList>> getDeviceDetails(@PathVariable String id) {
        final List<DeviceDetailList> deviceDetail = infoService.getDeviceDetail(id);
        return ResponseEntity.ok(deviceDetail);
    }

    @PostMapping
    public ResponseEntity<DeviceDetailList> saveDeviceDetails(@RequestBody DeviceDetailList deviceDetailList) {
        final DeviceDetailList deviceDetailLists = infoService.saveDeviceDetail(deviceDetailList);
        return ResponseEntity.ok(deviceDetailLists);
    }
}
