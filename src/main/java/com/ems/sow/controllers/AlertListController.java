package com.ems.sow.controllers;

import com.ems.sow.model.AlertsData;
import com.ems.sow.services.AlertListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/alerts")
public class AlertListController {

    private static final Logger logger = LoggerFactory.getLogger(AlertListController.class);


    @Autowired
    private AlertListService alertListService;

    @GetMapping("/{serialNumber}/{deviceModbus}")
    public ResponseEntity<List<AlertsData>> getAlertBySerialNumberAndOSD(@PathVariable String serialNumber,
                                                                        @PathVariable String deviceModbus) {
        logger.info("Fetching alerts for serialNumber={} and deviceModbus={}", serialNumber, deviceModbus);
        try {
            List<AlertsData> alerts = alertListService.getAlertBySerialNumberAndOSD(serialNumber, deviceModbus);
            if (alerts.isEmpty()) {
                logger.info("No alerts found for serialNumber={} and deviceModbus={}", serialNumber, deviceModbus);
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(alerts);
        } catch (Exception ex) {
            logger.error("Error fetching alerts for serialNumber={} and deviceModbus={}: {}", serialNumber, deviceModbus, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public List<AlertsData> getAlerts(
            @RequestParam String osd,
            @RequestParam String mdbid) {
        return alertListService.getAlertsByOsdAndMdbid(osd, mdbid);
    }

}
