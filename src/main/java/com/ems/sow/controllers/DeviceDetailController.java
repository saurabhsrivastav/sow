package com.ems.sow.controllers;

import com.ems.sow.model.RtuDetails;
import com.ems.sow.projection.IDeviceDetailList;
import com.ems.sow.projection.IDeviceListProj;
import com.ems.sow.services.DeviceDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/device-details")
public class DeviceDetailController {

    Logger logger = LoggerFactory.getLogger(DeviceDetailController.class);

    @Autowired
    private DeviceDetailService detailService;

    @GetMapping("/lists/{id}")
    public ResponseEntity<List<RtuDetails>> getUnregisteredDevicesList(@PathVariable("id") String id) {
        logger.info("get unregistered devices: ");
        final List<RtuDetails> deviceDetails = detailService.getDeviceByCustomerId(id);
        return ResponseEntity.ok(deviceDetails);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Optional<List<IDeviceDetailList>>> getDevices(@PathVariable("id") String id) {
        logger.info("get devices: ");
        final Optional<List<IDeviceDetailList>> deviceDetails = detailService.getDevices(id);
        return ResponseEntity.ok(deviceDetails);
    }

    @GetMapping("/device-list/{id}")
    public ResponseEntity<List<IDeviceListProj>> getDeviceById(@PathVariable("id") String id) {
        logger.info("get devices by Id:");
        return ResponseEntity.ok(detailService.findDeviceStatus(id));
    }

    @GetMapping("/device-serial-number/{id}")
    public ResponseEntity<List<RtuDetails>> getDeviceSerialNumberByCustomerId(@PathVariable("id") String id) {
        logger.info("get device serial number by customer id: ");
        return ResponseEntity.ok(detailService.findSerialNumbersByCustomerId(id));
    }

    @GetMapping("/device-by-id/{rtuId}")
    public ResponseEntity<List<RtuDetails>> getDeviceByRtuId(@PathVariable("rtuId") String rtuId) {
        logger.info("get device by rtu id: ");
        final List<RtuDetails> deviceDetails = detailService.findDeviceByRtuId(rtuId);
        return ResponseEntity.ok(deviceDetails);
    }

    @GetMapping
    public ResponseEntity<List<RtuDetails>> getAllDeviceDetails() {
        logger.info("get all device details: ");
        return ResponseEntity.ok(detailService.getAllDeviceDetails());
    }

    @PostMapping
    public ResponseEntity<RtuDetails> addDevice(@RequestBody RtuDetails deviceDetails) {
        logger.info("add device details: ");
        final RtuDetails deviceList = detailService.saveDeviceDetails(deviceDetails);
        return ResponseEntity.ok(deviceList);
    }

    @PutMapping
    public ResponseEntity<RtuDetails> updateDevice(@RequestBody RtuDetails deviceDetails) {
        logger.info("update device details: ");
        final RtuDetails deviceList = detailService.updateDeviceDetails(deviceDetails);
        return ResponseEntity.ok(deviceList);
    }

    @PutMapping("/uninstall")
    public ResponseEntity<RtuDetails> uninstalledDevice(@RequestBody RtuDetails deviceDetails) {
        logger.info("uninstall device details: ");
        final RtuDetails deviceList = detailService.uninstallDevice(deviceDetails);
        return ResponseEntity.ok(deviceList);
    }
}
