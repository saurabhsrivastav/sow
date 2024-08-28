package com.ems.sow.controllers;

import com.ems.sow.entities.CustomerList;
import com.ems.sow.services.CustomerListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/customer-list")
public class CustomerListController {

    @Autowired
    private CustomerListService customerListService;

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

    @PostMapping
    private ResponseEntity<CustomerList> addNewCustomer(@RequestBody CustomerList customerList) {
        return ResponseEntity.ok(customerListService.createCustomer(customerList));
    }

}
