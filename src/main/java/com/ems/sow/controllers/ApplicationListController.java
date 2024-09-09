package com.ems.sow.controllers;

import com.ems.sow.model.ApplicationList;
import com.ems.sow.projection.IApplicationListProj;
import com.ems.sow.services.ApplicationListService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/application-list")
public class ApplicationListController {

    Logger logger = org.slf4j.LoggerFactory.getLogger(ApplicationListController.class);

    @Autowired
    private ApplicationListService applicationListService;

    @PostMapping
    private ResponseEntity<ApplicationList> createApplication(@RequestBody ApplicationList list) {
        logger.info("Request to create application {}", list);
        final ApplicationList application = applicationListService.createApplication(list);
        return ResponseEntity.status(HttpStatus.CREATED).body(application);
    }

    @GetMapping(value = "/list")
    private ResponseEntity<List<ApplicationList>> getApplicationDetails() {
        logger.info("Request to get application details");
        final List<ApplicationList> list = applicationListService.getAllApplicationList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity<List<IApplicationListProj>> getAppDetailWithActiveCustomerCount() {
        logger.info("Request to get application details with active customer count");
        List<IApplicationListProj> activeCustomerCount = applicationListService.getActiveCustomerCount();
        return new ResponseEntity<>(activeCustomerCount, HttpStatus.OK);
    }


    @PutMapping(value = "/update-status")
    private ResponseEntity<ApplicationList> updateStatus(@RequestBody ApplicationList list) {
        logger.info("Request to update status of customer{}", list);
        try {
            final ApplicationList updateApplication = applicationListService.updateStatus(list);
            return ResponseEntity.ok(updateApplication);
        } catch (DataIntegrityViolationException e) {
            logger.error("Exception occurred {}", e);
            return null;
        }
    }
}
