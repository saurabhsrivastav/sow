package com.ems.sow.controllers;

import com.ems.sow.exceptions.ErrorResponse;
import com.ems.sow.model.CustomerList;
import com.ems.sow.projection.ICustomerListProj;
import com.ems.sow.services.CustomerListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customer-list")
public class CustomerListController {

    private static final Logger log = LoggerFactory.getLogger(CustomerListController.class);

    @Autowired
    private CustomerListService customerListService;

    // create new customer
    @PostMapping("/add")
    private ResponseEntity<?> createNewCustomer (
            @RequestParam("customerName") String customerName,
            @RequestParam("address") String address,
            @RequestParam("contactPerson") String contactPerson,
            @RequestParam("subStartDate") String subStartDate,
            @RequestParam("subEndDate") String subEndDate,
            @RequestParam("applicationId") String applicationId,
            @RequestPart("image") MultipartFile file) throws IOException {

        log.info("Inside createNewCustomer");

        CustomerList customerList = CustomerList.builder()
                .customerName(customerName)
                .address(address)
                .contactPerson(contactPerson)
                .subStartDate(subStartDate)
                .subEndDate(subEndDate)
                .applicationId(applicationId)
                .image(file.getBytes())
                .build();
        try {
            log.info("calling create customer service : ");
            CustomerList list = customerListService.createCustomer(customerList);
            list.setImage(null);
            log.info("Response from create customer: " + list);
            return ResponseEntity.status(HttpStatus.OK).body(list);
        } catch (Exception e) {
            log.error("Exception occurred: ", e);
            ErrorResponse errorResponse = new ErrorResponse(
                    "Failed to create new customer",
                    e.getMessage(),  // or a more generic message to avoid exposing internal details
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorResponse);
        }
    }

    // get All Customers
    @GetMapping("/all")
    private ResponseEntity<List<ICustomerListProj>> getAllCustomer() {
        log.info("Calling customer by id : ");
        List<ICustomerListProj> customers = customerListService.getAllCustomers();
        log.info("Response from get customer by id: {}", customers);
        return ResponseEntity.ok().body(customers);
    }

    // get customer detail with device and site by customer id
    @GetMapping("/{id}")
    private ResponseEntity<List<ICustomerListProj>> getCustomerByApplicationId(@PathVariable String id) {
        log.info("Calling customer by id : ");
        List<ICustomerListProj> customers = customerListService.getCustomerByApplicationId(id);
        log.info("Response from get customer by id: {}", customers);
        return ResponseEntity.ok().body(customers);
    }

    // load images
    @GetMapping("/image/{id}")
    private List<CustomerList> getImage(@PathVariable String id) {
        log.info("Calling device and site customer by id : ");
        final List<CustomerList> deviceAndSite = customerListService.loadCustomerImage(id);
        log.info("Response customer detail from get customer by id: {}", deviceAndSite);
        return ResponseEntity.ok(deviceAndSite).getBody();
    }

    // update
    @PutMapping (value = "/update-customer")
    private ResponseEntity<CustomerList> updateCustomerDetails(@RequestBody CustomerList customerList) {
        try {
            log.info("calling update customer service implementation : ");
            final CustomerList list = customerListService.updateCustomer(customerList);
            return ResponseEntity.status(HttpStatus.OK).body(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // delete
    @DeleteMapping ("/{id}")
    private ResponseEntity<CustomerList> deleteCustomer(@PathVariable String id) {
        try {
            customerListService.deleteCustomer(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
