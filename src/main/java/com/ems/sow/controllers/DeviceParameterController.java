package com.ems.sow.controllers;

import com.ems.sow.dto.InstallDeviceDTO;
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

    private static final Logger logger = LoggerFactory.getLogger(DeviceParameterController.class);

    private final DeviceParameterService deviceParameterService;
    private final RtuDetailRepository rtuDetailRepository;

    @Autowired
    public DeviceParameterController(DeviceParameterService deviceParameterService, RtuDetailRepository rtuDetailRepository) {
        this.deviceParameterService = deviceParameterService;
        this.rtuDetailRepository = rtuDetailRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> saveDeviceDetails(@RequestBody InstallDeviceDTO installDeviceDTO) {
        logger.info("Received request to save device parameters: {}", installDeviceDTO);

        if (installDeviceDTO == null || installDeviceDTO.getSerialNumber() == null || installDeviceDTO.getDeviceModbus() == null) {
            logger.error("Invalid device details: {}", installDeviceDTO);
            return generateErrorResponse("Invalid device details", HttpStatus.BAD_REQUEST);
        }

        boolean deviceExists = deviceParameterService.findBySerialNumberAndDeviceModbus(
                installDeviceDTO.getSerialNumber(), installDeviceDTO.getDeviceModbus());
        logger.info("Device exists: {}", deviceExists);

        if (deviceExists) {
            logger.error("Device already exists with same Serial Number and Modbus");
            return generateResponse("FOUND", "Device already exists with same Serial Number and Modbus", HttpStatus.OK);
        }

        if (!StringUtils.hasText(installDeviceDTO.getDeviceId()) || !deviceExists) {
            logger.info("Device not found, creating new device");
            return processDeviceSave(installDeviceDTO);
        }
        return generateResponse("FOUND", "Device already installed with same Serial Number and Modbus", HttpStatus.OK);
    }

    private ResponseEntity<Map<String, String>> processDeviceSave(InstallDeviceDTO device) {
        logger.info("Controller in processDeviceSave device details: {}", device);

        InstallDevice savedDevice = deviceParameterService.saveParameters(device);

        if (savedDevice == null) {
            logger.error("Failed to save device details: {}", device);
            return generateErrorResponse("Failed to save device details", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("Device details saved successfully: {}", savedDevice);
        try {
            logger.info("Persisting RTU details for device {}", savedDevice.getDeviceId());
            persistInRTUDetails(savedDevice.getDeviceId(), savedDevice.getSerialNumber());
            logger.info("RTU details persisted successfully for device {}", savedDevice.getDeviceId());
        } catch (Exception e) {
            logger.error("Error persisting RTU details for device {}: {}", savedDevice.getDeviceId(), e.getMessage());
            return generateErrorResponse("Device saved but failed to update RTU details", HttpStatus.PARTIAL_CONTENT);
        }
        return generateResponse("SUCCESS", "Device details saved successfully", HttpStatus.OK);
    }

    private void persistInRTUDetails(String deviceId, String serialNumber) {
        logger.info("Persisting RTU details for device {} with serial number {}", deviceId, serialNumber);
        List<RtuDetails> rtuDetailsList = rtuDetailRepository.findBySerialNumber(serialNumber);
        logger.info("Found {} RTU details for serial number {}", rtuDetailsList.size(), serialNumber);
        for (RtuDetails rtuDetail : rtuDetailsList) {
            rtuDetail.setDevice(deviceId);
        }
        logger.info("Updated {} RTU details with device ID {}", rtuDetailsList.size(), deviceId);
        rtuDetailRepository.saveAll(rtuDetailsList);
        logger.info("RTU details updated successfully for device {}", deviceId);
    }

    private ResponseEntity<Map<String, String>> generateErrorResponse(String message, HttpStatus status) {
        return ResponseEntity.status(status).body(Map.of("status", "ERROR", "message", message));
    }

    private ResponseEntity<Map<String, String>> generateResponse(String status, String message, HttpStatus statusCode) {
        return ResponseEntity.status(statusCode).body(createResponseMap(status, message));
    }

    private Map<String, String> createResponseMap(String status, String message) {
        Map<String, String> response = new HashMap<>();
        response.put("status", status);
        response.put("message", message);
        return response;
    }

    @GetMapping("/{serialNumber}")
    public ResponseEntity<List<InstallDevice>> getDeviceDetails(@PathVariable String serialNumber) {
        logger.info("Fetching device parameters for serial number: {}", serialNumber);
        List<InstallDevice> devices = deviceParameterService.getDeviceDetails(serialNumber);
        return ResponseEntity.ok(devices);
    }

    @GetMapping("/device/{customerId}")
    public ResponseEntity<List<InstallDevice>> getDeviceDetailsByCustomerID(@PathVariable String customerId) {
        logger.info("Fetching device details for customer ID: {}", customerId);
        List<InstallDevice> devices = deviceParameterService.getDeviceDetailsbyCustID(customerId);
        return ResponseEntity.ok(devices);
    }

}


























//    private static final Logger logger = LoggerFactory.getLogger(DeviceParameterController.class);
//
//    private final DeviceParameterService deviceParameterService;
//    private final RtuDetailRepository rtuDetailRepository;
//
//    public DeviceParameterController(DeviceParameterService deviceParameterService, RtuDetailRepository rtuDetailRepository) {
//        this.deviceParameterService = deviceParameterService;
//        this.rtuDetailRepository = rtuDetailRepository;
//    }
//
//    @PostMapping("/add")
//    public ResponseEntity<Map<String, String>> saveDeviceDetails(@RequestBody InstallDevice device) {
//        logger.info("Received request to save device parameters: {}", device);
//        if (device == null) {
//            return generateErrorResponse("Invalid device details", HttpStatus.BAD_REQUEST);
//        }
//        boolean deviceExists = deviceParameterService.findBySerialNumberAndDeviceModbus(
//                device.getSerialNumber(), device.getDeviceModbus()
//        );
//
//        if (!StringUtils.hasText(device.getDeviceId())) {
//            return handleNewDevice(device);
//        } else if (!deviceExists) {
//            return handleExistingDevice(device);
//        }
//
//        return generateResponse("FOUND", "Device already installed with same Serial Number and Modbus", HttpStatus.OK);
//    }
//
//    private ResponseEntity<Map<String, String>> handleNewDevice(InstallDevice device) {
//        InstallDevice savedDevice = deviceParameterService.saveParameters(device);
//
//        if (savedDevice == null) {
//            return generateErrorResponse("Failed to save device details", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        try {
//            persistInRTUDetails(savedDevice.getDeviceId(), savedDevice.getSerialNumber());
//        } catch (Exception e) {
//            logger.error("Error persisting RTU details for device {}: {}", savedDevice.getDeviceId(), e.getMessage());
//            return generateErrorResponse("Device saved but failed to update RTU details", HttpStatus.PARTIAL_CONTENT);
//        }
//        return generateResponse("SUCCESS", "Device details saved successfully", HttpStatus.OK);
//    }
//
//    private ResponseEntity<Map<String, String>> handleExistingDevice(InstallDevice device) {
//        InstallDevice savedDevice = deviceParameterService.saveParameters(device);
//        if (savedDevice == null) {
//            return generateErrorResponse("Failed to save device details", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        try {
//            persistInRTUDetails(savedDevice.getDeviceId(), savedDevice.getSerialNumber());
//        } catch (Exception e) {
//            logger.error("Error persisting RTU details for device {}: {}", savedDevice.getDeviceId(), e.getMessage());
//            return generateErrorResponse("Device saved but failed to update RTU details", HttpStatus.PARTIAL_CONTENT);
//        }
//        return generateResponse("SUCCESS", "Device details saved successfully", HttpStatus.OK);
//    }
//
//
//    private void persistInRTUDetails(String deviceId, String serialNumber) {
//        List<RtuDetails> rtuDetailsList = rtuDetailRepository.findBySerialNumber(serialNumber);
//        rtuDetailsList.forEach(rtuDetail -> {
//            rtuDetail.setDevice(deviceId);
//            rtuDetailRepository.save(rtuDetail);
//        });
//    }
//
//    private ResponseEntity<Map<String, String>> generateErrorResponse(String message, HttpStatus status) {
//        return ResponseEntity.status(status).body(Map.of("status", "ERROR", "message", message));
//    }
//
//    private ResponseEntity<Map<String, String>> generateResponse(String status, String message, HttpStatus statusCode) {
//        return ResponseEntity.status(statusCode).body(Map.of("status", status, "message", message));
//    }
//

