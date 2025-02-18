package com.ems.sow.controllers;

import com.ems.sow.model.InstallDevice;
import com.ems.sow.model.RtuDetails;
import com.ems.sow.repositories.RtuDetailRepository;
import com.ems.sow.services.DeviceParameterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/device-parameters")
public class DeviceParameterController {

    Logger logger = LoggerFactory.getLogger(DeviceParameterController.class);

    @Autowired
    private DeviceParameterService deviceParameterService;

    @Autowired
    RtuDetailRepository rtuDetailRepository;

    @PostMapping (value="/add")
    public ResponseEntity<InstallDevice> saveDeviceDetails(@RequestBody InstallDevice device) {
        logger.info("Request to save device parameters {}", device);
        final InstallDevice installDevice = deviceParameterService.saveParameters(device);
        persistInRTUDetails(installDevice.getDeviceId(), installDevice.getSerialNumber());
        return ResponseEntity.ok(installDevice);
    }


    void persistInRTUDetails(String deviceId, String serialNumber) {
        List<RtuDetails> bySerialNumber = rtuDetailRepository.findBySerialNumber(serialNumber);
        for (RtuDetails list : bySerialNumber) {
            list.setDevice(deviceId);
            rtuDetailRepository.save(list);
        }
    }

    @GetMapping("/{serialNumber}")
    public ResponseEntity<List<InstallDevice>> getDeviceDetails(@PathVariable String serialNumber) {
        logger.info("Request to get device parameters {}", serialNumber);
        final List<InstallDevice> deviceDetailLists = deviceParameterService.getDeviceDetails(serialNumber);
        return ResponseEntity.ok(deviceDetailLists);
    }

    @GetMapping("/device/{customerId}")
    public ResponseEntity<List<InstallDevice>> getDeviceDetailsByCustomerID(@PathVariable String customerId) {
        logger.info("Request to get device details by customer id{}", customerId);
        final List<InstallDevice> deviceDetailLists = deviceParameterService.getDeviceDetailsbyCustID(customerId);
        return ResponseEntity.ok(deviceDetailLists);
    }

}
