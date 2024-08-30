package com.ems.sow.controllers;


import com.ems.sow.model.SiteDetails;
import com.ems.sow.services.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/site")
public class SiteDetailController {

    @Autowired
    private SiteService siteService;


    @GetMapping
    public ResponseEntity<List<SiteDetails>> getAllSites() {

        return ResponseEntity.ok(siteService.getAllSitesList());
    }
}

