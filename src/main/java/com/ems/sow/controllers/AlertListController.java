package com.ems.sow.controllers;

import com.ems.sow.model.AlertList;
import com.ems.sow.services.AlertListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/alerts")
public class AlertListController {

    @Autowired
    private AlertListService alertListService;

    /**
     * Get All Alert List
     *
     * @return
     */
    @GetMapping(value = "/{id}")
    private ResponseEntity<List<AlertList>> getAlertByCustomerId(@PathVariable Integer id) {
        return ResponseEntity.ok(alertListService.getAlertByCustomerId(id));
    }
}
