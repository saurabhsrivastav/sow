package com.ems.sow.controllers;

import com.ems.sow.model.RtuDetails;
import com.ems.sow.projection.IRtuDetailList;
import com.ems.sow.projection.IRtuListProj;;
import com.ems.sow.repositories.RtuDetailRepository;
import com.ems.sow.services.RtuDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/rtu-details")
public class RtuController {

    Logger logger = LoggerFactory.getLogger(RtuController.class);

    @Autowired
    RtuDetailService rtuDetailService;

    @Autowired
    RtuDetailRepository repository;

    @GetMapping("/lists/{id}")
    public ResponseEntity<List<RtuDetails>> getUnregisteredRtuList(@PathVariable("id") String id) {
        logger.info("get detail list of unregistered RTUs: ");
        final List<RtuDetails> rtuDetails = rtuDetailService.getRtuByCustomerId(id);
        return ResponseEntity.ok(rtuDetails);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Optional<List<IRtuDetailList>>> getRtu(@PathVariable("id") String id) {
        logger.info("get devices: ");
        final Optional<List<IRtuDetailList>> rtu = rtuDetailService.getRtu(id);
        return ResponseEntity.ok(rtu);
    }

    @GetMapping("/device-list/{id}")
    public ResponseEntity<List<IRtuListProj>> getRtuById(@PathVariable("id") String id) {
        logger.info("get devices by Id:");
        return ResponseEntity.ok(rtuDetailService.findRtuStatus(id));
    }

    @GetMapping("/device-serial-number/{id}")
    public ResponseEntity<List<RtuDetails>> getDeviceSerialNumberByCustomerId(@PathVariable("id") String id) {
        logger.info("get device serial number by customer id: ");
        return ResponseEntity.ok(rtuDetailService.findSerialNumbersByCustomerId(id));
    }

    @GetMapping("/device-by-id/{rtuId}")
    public ResponseEntity<List<RtuDetails>> getRtuByRtuId(@PathVariable("rtuId") String rtuId) {
        logger.info("get device by rtu id: ");
        final List<RtuDetails> deviceDetails = rtuDetailService.findRtuByRtuId(rtuId);
        return ResponseEntity.ok(deviceDetails);
    }

    @GetMapping
    public ResponseEntity<List<RtuDetails>> getAllRtuDetails() {
        logger.info("get all device details: ");
        return ResponseEntity.ok(rtuDetailService.getAllRtuDetails());
    }

    @PostMapping
    public ResponseEntity<RtuDetails> addRtu(@RequestBody RtuDetails deviceDetails) {
        logger.info("add device details: ");
        final RtuDetails deviceList = rtuDetailService.saveRtu(deviceDetails);
        return ResponseEntity.ok(deviceList);
    }

    @PutMapping("/update")
    public ResponseEntity<RtuDetails> updateRtu(@RequestBody RtuDetails deviceDetails) {
        logger.info("update device details: ");
        final RtuDetails deviceList = rtuDetailService.updateRtuDetails(deviceDetails);
        return ResponseEntity.ok(deviceList);
    }

    @PutMapping("/uninstall")
    public ResponseEntity<List<RtuDetails>> uninstalledRtu(@RequestBody RtuDetails deviceDetails) {
        logger.info("uninstall device details: ");
        final List<RtuDetails> deviceList = rtuDetailService.uninstallRtu(deviceDetails);
        return ResponseEntity.ok(deviceList);
    }
}
