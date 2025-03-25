package com.ems.sow.controllers;

import com.ems.sow.model.ImagesStock;
import com.ems.sow.services.DeviceParameterImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/device-parameters-image-detail")
public class DeviceParameterImageController {

    @Autowired
    private DeviceParameterImageService parameterImageService;

    @GetMapping("/{osd}/{modbusId}")
    public ResponseEntity<List<ImagesStock>> getImages(@PathVariable String osd,
                                       @PathVariable String modbusId) {
        try {
            List<ImagesStock> images = parameterImageService.getListImages(osd, modbusId);
            if (images.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(images);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }
    }

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam String fileUrl) {
        try {
            // Open a connection to the file URL
            URL url = new URL(fileUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            // Check if the response is OK
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return ResponseEntity.badRequest().build();
            }

            // Read the file stream
            InputStream inputStream = new BufferedInputStream(connection.getInputStream());

            // Set headers for the file download
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"output.jpg\"");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new InputStreamResource(inputStream));

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
