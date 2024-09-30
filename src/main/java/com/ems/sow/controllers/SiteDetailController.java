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

    @Autowired
    private SiteService siteService;

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
    public ResponseEntity<List<SiteDetails>> getSiteById(@PathVariable String id) {
        final List<SiteDetails> siteById = siteService.getSiteByCustomerId(id);
        return ResponseEntity.ok(siteById);
    }

    @GetMapping("map-view/{name}")
    public ResponseEntity<List<SiteDetails>> getSiteDetailByName(@PathVariable String name) {
        final List<SiteDetails> siteByName = siteService.getSiteByName(name);
        return ResponseEntity.ok(siteByName);
    }
}

