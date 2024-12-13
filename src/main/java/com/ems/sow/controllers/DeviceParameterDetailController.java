package com.ems.sow.controllers;

import com.ems.sow.model.StreamData;
import com.ems.sow.services.DeviceParameterDetailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/device-parameters-detail")
public class DeviceParameterDetailController {

    @Autowired
    DeviceParameterDetailService deviceParameterDetailService;

    @GetMapping("/{serialNumber}/{deviceModbus}")
    public ResponseEntity<?> getDeviceDetails( @PathVariable String serialNumber,
                                               @PathVariable String deviceModbus) throws JsonProcessingException {

        try {
            if (serialNumber == null || serialNumber.isEmpty() || deviceModbus == null || deviceModbus.isEmpty()) {
                return ResponseEntity.badRequest().body("serialNumber or deviceModbus cannot be null or empty");
            }

            List<Map> jsonDataAsMap = deviceParameterDetailService.getJsonDataAsMap(serialNumber, deviceModbus);

            if (jsonDataAsMap == null || jsonDataAsMap.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No device details found for the given parameters");
            }
            return ResponseEntity.ok(jsonDataAsMap);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing JSON data: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred: " + e.getMessage());
        }
    }
}
