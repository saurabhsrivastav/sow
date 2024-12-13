package com.ems.sow.controllers;


import com.ems.sow.model.User;
import com.ems.sow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

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
    private ResponseEntity<User> registerUser(@RequestBody User user){
        final User response = userService.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
