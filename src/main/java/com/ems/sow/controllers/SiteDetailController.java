package com.ems.sow.controllers;

import com.ems.sow.exceptions.ErrorResponse;
import com.ems.sow.model.SiteDetails;
import com.ems.sow.projection.ISiteDetailsProj;
import com.ems.sow.services.SiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/site")
public class SiteDetailController {

    private static final Logger logger = LoggerFactory.getLogger(SiteDetailController.class);
    @Autowired
    private SiteService siteService;

    @PostMapping("/add")
    public ResponseEntity<?> createSite(
            @RequestParam String selectedSiteId,
            @RequestParam String siteName,
            @RequestParam String siteAddress,
            @RequestParam String geofenceRadius,
            @RequestParam String latitude,
            @RequestParam String longitude,
            @RequestParam String location,
            @RequestParam String customerId,
            @RequestPart("image") MultipartFile file) {

        logger.info("Adding new site: {}", siteName);

        try {
            SiteDetails siteDetails = SiteDetails.builder()
                    .selectedSiteId(selectedSiteId)
                    .siteName(siteName)
                    .siteAddress(siteAddress)
                    .geofenceRadius(geofenceRadius)
                    .latitude(latitude)
                    .longitude(longitude)
                    .location(location)
                    .customerId(customerId)
                    .image(file.getBytes())
                    .build();

            SiteDetails savedSite = siteService.createSite(siteDetails);
            logger.info("Site added successfully: {}", savedSite);

            return ResponseEntity.ok(savedSite);

        } catch (IOException e) {
            logger.error("Error reading image file: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Invalid file format", e.getMessage(), HttpStatus.BAD_REQUEST));

        } catch (Exception e) {
            logger.error("Error adding site: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Failed to add new site", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping
    public ResponseEntity<List<SiteDetails>> getAllSites() {
        logger.info("Fetching all sites");
        return ResponseEntity.ok(siteService.getAllSitesList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<SiteDetails>> getSiteByCustomerId(@PathVariable String id) {
        logger.info("Fetching sites for customerId: {}", id);
        return ResponseEntity.ok(siteService.getSiteByCustomerId(id));
    }

    @GetMapping("site-detail/{id}")
    public ResponseEntity<List<ISiteDetailsProj>> getSiteDetailById(@PathVariable String id) {
        logger.info("Fetching site details for siteId: {}", id);
        return ResponseEntity.ok(siteService.getSiteById(id));
    }

}

