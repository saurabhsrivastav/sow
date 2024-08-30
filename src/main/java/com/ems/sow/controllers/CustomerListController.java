package com.ems.sow.controllers;

import com.ems.sow.model.ApplicationList;
import com.ems.sow.model.CustomerList;
import com.ems.sow.services.CustomerListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/customer-list")
public class CustomerListController {

    @Autowired
    private CustomerListService customerListService;

    @PostMapping
    private ResponseEntity<CustomerList> addNewCustomer(@RequestBody CustomerList customerList) {
        try {
            final CustomerList list = customerListService.createCustomer(customerList);
            return ResponseEntity.status(HttpStatus.CREATED).body(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    private ResponseEntity<List<CustomerList>> getAllCustomerList() {
        return ResponseEntity.ok(customerListService.getAllCustomersList());
    }

    @GetMapping(value = "/{id}")
    private ResponseEntity<Optional<List<CustomerList>>> getCustomerList(@PathVariable Integer id) {
        final Optional<List<CustomerList>> customer = customerListService.getCustomerById(id);
        if (customer.isPresent()) {
            return ResponseEntity.ok(customer);
        }
        return null;
    }



}
