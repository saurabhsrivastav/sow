package com.ems.sow.controllers;


import com.ems.sow.dto.LoginRequest;
import com.ems.sow.exceptions.ErrorResponse;
import com.ems.sow.model.User;
import com.ems.sow.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody LoginRequest loginRequest) {
        logger.info("Fetching user details for username: {}", loginRequest.getUsername());
        User user = userService.findByUserNameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        if (user == null) {
            logger.warn("Unauthorized access attempt for username: {}", loginRequest.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register-user")
    private ResponseEntity<?> registerUser( @RequestParam("name") String name,
                                             @RequestParam("username") String username,
                                             @RequestParam("password") String password,
                                             @RequestParam("customerId") String customerId,
                                             @RequestParam("role") String role,
                                             @RequestPart(value = "image", required = false) MultipartFile file) throws IOException  {

        byte[] imageBytes = null;
        if (file != null && !file.isEmpty()) {
            imageBytes = file.getBytes();
        }
        User user = User.builder()
                .name(name).userName(username).password(password)
                .customerId(customerId).role(role).image(imageBytes)
                .build();
        try {
            logger.info("calling create user service : ");
            final User response = userService.register(user);
            response.setImage(null);
            logger.info("Response from create user service: {}", response);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            logger.error("Exception occurred: ", e);
            ErrorResponse errorResponse = new ErrorResponse("Failed to create new user", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
