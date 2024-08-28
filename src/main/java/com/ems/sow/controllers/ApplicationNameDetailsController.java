package com.ems.sow.controllers;

import com.ems.sow.entities.ApplicationList;
import com.ems.sow.entities.ApplicationNameDetails;
import com.ems.sow.repositories.ApplicationNameDetailsRepository;
import com.ems.sow.services.ApplicationNameDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin (origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/appname")
public class ApplicationNameDetailsController {


    @Autowired
    private ApplicationNameDetailsService nameDetailsService;

    @GetMapping
    public ResponseEntity<List<ApplicationNameDetails>> getApplicationNameDetails() {
        return ResponseEntity.ok(nameDetailsService.getApplicationDetails());
    }
}
