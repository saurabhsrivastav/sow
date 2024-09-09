package com.ems.sow.controllers;


import com.ems.sow.model.SiteDetails;
import com.ems.sow.services.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/site")
public class SiteDetailController {

    private final SiteService siteService;

    @Autowired
    public SiteDetailController(SiteService siteService) {
        this.siteService = siteService;
    }

    @PostMapping
    public ResponseEntity<SiteDetails> addNewSite(@RequestBody SiteDetails siteDetails) {
        final SiteDetails site = siteService.createSite(siteDetails);
        return ResponseEntity.ok(site);
    }
    @GetMapping
    public ResponseEntity<List<SiteDetails>> getAllSites() {
        return ResponseEntity.ok(siteService.getAllSitesList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<SiteDetails>> getSitesById(@PathVariable String id) {
        final List<SiteDetails> siteById = siteService.getSiteById(id);
        return ResponseEntity.ok(siteById);
    }
}

