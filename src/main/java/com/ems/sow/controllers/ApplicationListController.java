package com.ems.sow.controllers;

import com.ems.sow.model.ApplicationList;
import com.ems.sow.projection.IApplicationListProj;
import com.ems.sow.services.ApplicationListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/application-list")
public class ApplicationListController {

    @Autowired
    private ApplicationListService applicationListService;

    @PostMapping
    private ResponseEntity<ApplicationList> createApplication(@RequestBody ApplicationList list) {
        final ApplicationList application = applicationListService.createApplication(list);
        return ResponseEntity.status(HttpStatus.CREATED).body(application);
    }

    @GetMapping(value = "/list")
    private ResponseEntity<List<ApplicationList>> getApplicationDetails() {
        final List<ApplicationList> list = applicationListService.getAllApplicationList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity<List<IApplicationListProj>> getActiveCustomerCount() {
        List<IApplicationListProj> activeCustomerCount = applicationListService.getActiveCustomerCount();
        return new ResponseEntity<>(activeCustomerCount, HttpStatus.OK);
    }

}
