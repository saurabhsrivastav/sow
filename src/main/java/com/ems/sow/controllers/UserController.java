package com.ems.sow.controllers;


import com.ems.sow.model.User;
import com.ems.sow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    /*@Autowired
    private UserController(UserService userService) {
        this.userService = userService;
    }*/

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        final User response = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{passcode}")
    private ResponseEntity<User> getUserDetail(@PathVariable String passcode){
        final User response = userService.findByPasscode(passcode);
        return ResponseEntity.ok(response);
    }
}
