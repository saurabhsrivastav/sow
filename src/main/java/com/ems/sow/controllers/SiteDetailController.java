package com.ems.sow.controllers;


import com.ems.sow.exceptions.ErrorResponse;
import com.ems.sow.model.CustomerList;
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

    private static final Logger log = LoggerFactory.getLogger(SiteDetailController.class);

    @Autowired
    private SiteService siteService;

    // add new site
    @PostMapping("/add")
    private ResponseEntity<?> createNewCustomer (
            @RequestParam("selectedSiteId") String selectedSiteId,
            @RequestParam("siteName") String siteName,
            @RequestParam("siteAddress") String siteAddress,
            @RequestParam("geofenceRadius") String geofenceRadius,
            @RequestParam("latitude") String latitude,
            @RequestParam("longitude") String longitude,
            @RequestParam("location") String location,
            @RequestParam("customerId") String customerId,
            @RequestPart("image") MultipartFile file) throws IOException {

        log.info("Inside add new site");

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
        try {
            log.info("calling add new site service : ");
            SiteDetails list = siteService.createSite(siteDetails);
            list.setImage(null);
            log.info("Response from add new site: " + list);
            return ResponseEntity.status(HttpStatus.OK).body(list);
        } catch (Exception e) {
            log.error("Exception occurred: ", e);
            ErrorResponse errorResponse = new ErrorResponse(
                    "Failed to add new site",
                    e.getMessage(),  // or a more generic message to avoid exposing internal details
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorResponse);
        }
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

    @GetMapping("site-detail/{id}")
    public ResponseEntity<List<ISiteDetailsProj>> getSiteDetailByName(@PathVariable String id) {
        final List<ISiteDetailsProj> siteByName = siteService.getSiteById(id);
        return ResponseEntity.ok(siteByName);
    }

}

