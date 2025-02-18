package com.ems.sow.controllers;

import com.ems.sow.model.InstallDevice;
import com.ems.sow.model.RtuDetails;
import com.ems.sow.repositories.RtuDetailRepository;
import com.ems.sow.services.DeviceParameterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/device-parameters")
public class DeviceParameterController {

    Logger logger = LoggerFactory.getLogger(DeviceParameterController.class);

    @Autowired
    private DeviceParameterService deviceParameterService;

    @Autowired
    RtuDetailRepository rtuDetailRepository;

    @PostMapping(value = "/add")
    public ResponseEntity<Map<String, String>> saveDeviceDetails(@RequestBody InstallDevice device) {
        logger.info("Request to save device parameters {}", device);

        if (device == null) {
            return generateErrorResponse("Invalid device details", HttpStatus.BAD_REQUEST);
        }

        // If deviceId is missing, save as a new device
        if (!StringUtils.hasText(device.getDeviceId())) {
            return handleNewDevice(device);
        }

        // Check for existing device based on serial number and Modbus
        if (deviceParameterService.findBySerialNumberAndDeviceModbus(device.getSerialNumber(), device.getDeviceModbus())) {
            return generateResponse("FOUND", "Device already installed with same Serial Number and Modbus", HttpStatus.OK);
        }

        // Handle the case when device is being updated
        persistInRTUDetails(device.getDeviceId(), device.getSerialNumber());
        return generateResponse("UPDATED", "Device details updated successfully", HttpStatus.OK);
    }

    private ResponseEntity<Map<String, String>> handleNewDevice(InstallDevice device) {
        InstallDevice installDevice = deviceParameterService.saveParameters(device);

        if (installDevice == null) {
            return generateErrorResponse("Failed to save device details", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        persistInRTUDetails(installDevice.getDeviceId(), installDevice.getSerialNumber());
        return generateResponse("SUCCESS", "Device details saved successfully", HttpStatus.OK);
    }

    private ResponseEntity<Map<String, String>> generateErrorResponse(String message, HttpStatus status) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "ERROR");
        response.put("message", message);
        return ResponseEntity.status(status).body(response);
    }

    private ResponseEntity<Map<String, String>> generateResponse(String status, String message, HttpStatus statusCode) {
        Map<String, String> response = new HashMap<>();
        response.put("status", status);
        response.put("message", message);
        return ResponseEntity.status(statusCode).body(response);
    }

    public void persistInRTUDetails(String deviceId, String serialNumber) {
        List<RtuDetails> bySerialNumber = rtuDetailRepository.findBySerialNumber(serialNumber);
        for (RtuDetails rtuDetail : bySerialNumber) {
            rtuDetail.setDevice(deviceId);
            rtuDetailRepository.save(rtuDetail);
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
