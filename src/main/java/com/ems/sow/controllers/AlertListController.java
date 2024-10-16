                                     package com.ems.sow.controllers;

import com.ems.sow.model.AlertList;
import com.ems.sow.services.AlertListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/alerts")
public class AlertListController {

    Logger logger = Logger.getLogger(AlertListController.class.getName());

    @Autowired
    private AlertListService alertListService;

    @GetMapping(value = "/{id}")
    private ResponseEntity<List<AlertList>> getAlertDetailsByCustomerId(@PathVariable String id) {
        logger.info("entered in getAlertByCustomerId <" + id + "> customer id");
        return ResponseEntity.ok(alertListService.getAlertByCustomerId(id));
    }

    @GetMapping(value = "/device/{id}")
    private ResponseEntity<List<AlertList>> getAlertByDeviceId(@PathVariable String id) {
        logger.info("entered in getAlertByDeviceId <" + id + "> Device Id");
        return ResponseEntity.ok(alertListService.getAlertByDeviceId(id));
    }
}
