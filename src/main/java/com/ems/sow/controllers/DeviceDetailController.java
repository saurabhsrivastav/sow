package com.ems.sow.controllers;

import com.ems.sow.model.DeviceDetailList;
import com.ems.sow.model.DeviceList;
import com.ems.sow.projection.IDeviceDetailList;
import com.ems.sow.projection.IDeviceListProj;
import com.ems.sow.services.DeviceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/device-details")
public class DeviceDetailController {

    @Autowired
    private DeviceDetailService detailService;

    @PostMapping
    public ResponseEntity<?> addDevice(@RequestBody DeviceList deviceDetails) {
        final DeviceList deviceList = detailService.saveDeviceDetails(deviceDetails);
        return ResponseEntity.ok(deviceList);
    }
 
    @PutMapping
    public ResponseEntity<DeviceList> updateDevice(@RequestBody DeviceList deviceDetails) {
        final DeviceList deviceList = detailService.updateDeviceDetails(deviceDetails);
        return ResponseEntity.ok(deviceList);
    }
    @GetMapping
    public ResponseEntity<List<DeviceList>> getAllDeviceDetails() {
        return ResponseEntity.ok(detailService.getAllDeviceDetails());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<IDeviceDetailList>> getDeviceDetails(@PathVariable("id") String id) {
        final Optional<IDeviceDetailList> deviceDetails = detailService.getDeviceDetails(id);
        return ResponseEntity.ok(deviceDetails);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Optional<List<IDeviceDetailList>>> getDevices(@PathVariable("id") String id) {
        final Optional<List<IDeviceDetailList>> deviceDetails = detailService.getDevices(id);
        return ResponseEntity.ok(deviceDetails);
    }

    @GetMapping("/device-list/{id}")
    public ResponseEntity<List<IDeviceListProj>> getDeviceById(@PathVariable("id") String id) {
        return ResponseEntity.ok(detailService.findDevice(id));
    }


}
