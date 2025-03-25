package com.ems.sow.controllers;

import com.ems.sow.services.DeviceParameterDetailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/device-parameters-monitoring-detail")
public class DeviceParameterMonitoringController {

    @Autowired
    DeviceParameterDetailService deviceParameterDetailService;

    @GetMapping("/{serialNumber}/{deviceModbus}")
    public ResponseEntity<?> getDeviceDetails( @PathVariable String serialNumber,
                                               @PathVariable String deviceModbus,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size) throws JsonProcessingException {
        try {
            if (serialNumber == null || serialNumber.isEmpty() || deviceModbus == null || deviceModbus.isEmpty()) {
                return ResponseEntity.badRequest().body("serialNumber or deviceModbus cannot be null or empty");
            }
            Pageable pageable = PageRequest.of(page, size);
            Page<Map> jsonDataAsMap = deviceParameterDetailService.getJsonDataAsMap(serialNumber, deviceModbus, pageable);

            if (jsonDataAsMap == null || jsonDataAsMap.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No device details found for the given parameters");
            }
            return ResponseEntity.ok(jsonDataAsMap);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred: " + e.getMessage());
        }
    }


    @GetMapping("/filter/{serialNumber}/{deviceModbus}")
    public ResponseEntity<?> getFilteredData(
            @PathVariable String serialNumber,
            @PathVariable String deviceModbus,
            @RequestParam(required = false) String filterType,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate customStartDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate customEndDate) {

        try {
            if (serialNumber.isEmpty() || deviceModbus.isEmpty()) {
                return ResponseEntity.badRequest().body("serialNumber or deviceModbus cannot be empty");
            }

            // Fetch filtered data
            List<Map> jsonDataAsMap = deviceParameterDetailService.getFilteredData(serialNumber, deviceModbus, filterType, customStartDate, customEndDate);

            if (jsonDataAsMap.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No device details found");
            }
            return ResponseEntity.ok(jsonDataAsMap);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }

}
