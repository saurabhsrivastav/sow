package com.ems.sow.controllers;

import com.ems.sow.entities.LoginDetails;
import com.ems.sow.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<LoginDetails> getUser(@RequestBody String pwd) {
        LoginDetails loginDetails = loginService.getUser(pwd);
        return  ResponseEntity.status(HttpStatus.OK).body(loginDetails);

    }
}
