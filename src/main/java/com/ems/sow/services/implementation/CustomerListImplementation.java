package com.ems.sow.services.implementation;

import com.ems.sow.model.CustomerList;
import com.ems.sow.projection.ICustomerListProj;
import com.ems.sow.repositories.CustomerListRepository;
import com.ems.sow.services.CustomerListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


@Service
public class CustomerListImplementation implements CustomerListService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerListImplementation.class);

    @Autowired
    private CustomerListRepository customerListRepository;

    @Override
    public List<ICustomerListProj> getAllCustomers() {
        logger.info("getting all customers detail");
        return customerListRepository.findAllCustomer();
    }

    @Override
    public List<CustomerList> loadCustomerImage(String id) {
        return customerListRepository.findByCustomerId(id);
    }

    @Override
    public CustomerList createCustomer(CustomerList customerList) throws IOException {
        logger.info("creating new customer ");
        customerList.setCustomerId(UUID.randomUUID().toString());
        return customerListRepository.save(customerList);
    }

    @Override
    public CustomerList updateCustomer(CustomerList customerList) {
        logger.info("update customer");
        return customerListRepository.save(customerList);
    }

    @Override
    public void deleteCustomer(String id) {
        logger.info("delete customer");
        customerListRepository.deleteById(id);
    }

    @Override
    public List<ICustomerListProj> getCustomerByApplicationId(String id) {
        return customerListRepository.findByAppId(id);
    }

    @Override
    public List<CustomerList> getCustomerByUserName(String username) {
        return customerListRepository.findByUserName(username);
    }
}