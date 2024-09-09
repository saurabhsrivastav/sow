package com.ems.sow.controllers;

import com.ems.sow.model.CustomerList;
import com.ems.sow.projection.ICustomerListProj;
import com.ems.sow.services.CustomerListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    private ResponseEntity<List<ICustomerListProj>> getAllCustomerList() {
        final List<ICustomerListProj> allCustomersList = customerListService.getAllCustomersList();
        return ResponseEntity.ok(allCustomersList);
    }

    @GetMapping(value = "/{id}")
    private ResponseEntity<List<ICustomerListProj>> getCustomerList(@PathVariable String id) {
        final List<ICustomerListProj> customer = customerListService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @PutMapping(value = "/update-customer")
    private ResponseEntity<CustomerList> updateCustomer(@RequestBody CustomerList customerList) {
        try {
            final CustomerList list = customerListService.updateCustomer(customerList);
            return ResponseEntity.status(HttpStatus.OK).body(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<CustomerList> deleteCustomer(@PathVariable String id) {
        try {
            customerListService.deleteCustomer(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
