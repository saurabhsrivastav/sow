package com.ems.sow.controllers;


import com.ems.sow.exceptions.ErrorResponse;
import com.ems.sow.model.User;
import com.ems.sow.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/{email}/{password}")
    private ResponseEntity<User> getUserDetail(@PathVariable String email, @PathVariable String password){
        final User user = userService.findByEmailAndPassword(email, password);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register-user")
    private ResponseEntity<?> registerUser( @RequestParam("name") String name,
                                             @RequestParam("email") String email,
                                             @RequestParam("password") String password,
                                             @RequestParam("customerId") String customerId,
                                             @RequestParam("role") String role,
                                             @RequestPart(value = "image", required = false) MultipartFile file) throws IOException  {

        byte[] imageBytes = null;

        if (file != null && !file.isEmpty()) {
            imageBytes = file.getBytes();
        }

        final User user = User.builder().name(name).email(email).password(password).customerId(customerId).role(role).image(imageBytes)
                .build();

        try {
            log.info("calling create user service : ");
            final User response = userService.register(user);
            response.setImage(null);
            log.info("Response from create user service: {}", response);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            log.error("Exception occurred: ", e);
            ErrorResponse errorResponse = new ErrorResponse("Failed to create new user", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
