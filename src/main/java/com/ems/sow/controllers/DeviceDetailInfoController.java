package com.ems.sow.controllers;

import com.ems.sow.model.DeviceDetailList;
import com.ems.sow.projection.IDeviceDetailInfoList;
import com.ems.sow.services.DeviceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/device-details-info")
public class DeviceDetailInfoController {

    @Autowired
    private DeviceInfoService deviceInfoService;

    @GetMapping("/{id}")
    public ResponseEntity<List<DeviceDetailList>> getDeviceDetails(@PathVariable String id) {
        final List<DeviceDetailList> deviceDetail = deviceInfoService.getAllDeviceDetails(id);
        return ResponseEntity.ok(deviceDetail);
    }

    @PostMapping
    public ResponseEntity<DeviceDetailList> saveDeviceDetails(@RequestBody DeviceDetailList deviceDetailList) {
        final DeviceDetailList deviceDetailLists = deviceInfoService.saveDeviceDetail(deviceDetailList);
        return ResponseEntity.ok(deviceDetailLists);
    }

    @GetMapping("/device-site/{id}")
    public ResponseEntity<List<IDeviceDetailInfoList>> getDeviceAndSiteDetails(@PathVariable String id) {
        final List<IDeviceDetailInfoList> deviceDetail = deviceInfoService.getDeviceAndSiteDetails(id);
        return ResponseEntity.ok(deviceDetail);
    }

    @GetMapping("/device/{customerId}")
    public ResponseEntity<List<DeviceDetailList>> getDevicesDetail(@PathVariable String customerId) {
        final List<DeviceDetailList> deviceDetail = deviceInfoService.getDeviceDetailsByCustomerId(customerId);
        return ResponseEntity.ok(deviceDetail);
    }
}