package com.ems.sow.controllers;

import com.ems.sow.exceptions.ErrorResponse;
import com.ems.sow.model.CustomerList;
import com.ems.sow.projection.ICustomerListProj;
import com.ems.sow.services.CustomerListService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customer-list")
public class CustomerListController {

    private static final Logger log = LoggerFactory.getLogger(CustomerListController.class);

    @Autowired
    private CustomerListService customerListService;

    @PostMapping("/add")
    private ResponseEntity<?> createNewCustomer (@RequestParam("customerName") String customerName,
            @RequestParam("address") String address, @RequestParam("contactPerson") String contactPerson,
            @RequestParam("subStartDate") String subStartDate, @RequestParam("subEndDate") String subEndDate,
            @RequestParam("deviceCapping") String deviceCapping, @RequestParam("siteCapping") String siteCapping,
            @RequestParam("applicationId") String applicationId, @RequestParam("userName") String userName,
            @RequestPart("image") MultipartFile file) throws IOException {

        log.info("controller is in create new customer()");
        CustomerList customerList = CustomerList.builder().customerName(customerName).address(address)
                .contactPerson(contactPerson).subStartDate(subStartDate).subEndDate(subEndDate).deviceCapping(deviceCapping)
                .siteCapping(siteCapping).applicationId(applicationId).userName(userName).image(file.getBytes()).build();
        try {
            log.info("calling create customer service : ");
            CustomerList list = customerListService.createCustomer(customerList);
            list.setImage(null);
            log.info("Response from create customer: {}", list);
            return ResponseEntity.status(HttpStatus.OK).body(list);
        } catch (Exception e) {
            log.error("Exception occurred: ", e);
            ErrorResponse errorResponse = new ErrorResponse("Failed to create new customer",
                    e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/all")
    private ResponseEntity<List<ICustomerListProj>> getAllCustomer() {
        log.info("Calling getAllCustomer () : ");
        List<ICustomerListProj> customers = customerListService.getAllCustomers();
        return ResponseEntity.ok().body(customers);
    }

    @GetMapping("/{applicationId}")
    private ResponseEntity<List<ICustomerListProj>> getCustomerByApplicationId(@PathVariable String applicationId) {
        log.info("Calling getCustomer by applicationId() : ");
        List<ICustomerListProj> customers = customerListService.getCustomerByApplicationId(applicationId);
        log.info("Response from get customer by id: {}", customers);
        return ResponseEntity.ok().body(customers);
    }

    @GetMapping("/getuser/{userName}")
    private ResponseEntity<List<CustomerList>> getCustomerByUserName(@PathVariable String userName) {
        log.info("calling get customer by userName:");
        List<CustomerList> customers = customerListService.getCustomerByUserName(userName);
        log.info("response from get customer by userName: {}", customers);
        return ResponseEntity.ok().body(customers);
    }

    @GetMapping("/image/{id}")
    private List<CustomerList> getImage(@PathVariable String id) {
        log.info("Calling device and site customer by id : ");
        final List<CustomerList> deviceAndSite = customerListService.loadCustomerImage(id);
        log.info("Response customer detail from get customer by id: {}", deviceAndSite);
        return ResponseEntity.ok(deviceAndSite).getBody();
    }

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