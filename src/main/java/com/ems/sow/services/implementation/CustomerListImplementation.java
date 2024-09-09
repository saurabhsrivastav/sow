package com.ems.sow.services.implementation;

import com.ems.sow.model.CustomerList;
import com.ems.sow.projection.ICustomerListProj;
import com.ems.sow.repositories.CustomerListRepository;
import com.ems.sow.services.CustomerListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

/**
 * Customer List Implementation
 */

@Service
public class CustomerListImplementation implements CustomerListService {

    @Autowired
    private CustomerListRepository customerListRepository;

    @Override
    public List<ICustomerListProj> getAllCustomersList() {
        return customerListRepository.getAllCustomerDetails();
    }

    @Override
    public CustomerList createCustomer(CustomerList customerList) {
        String list = UUID.randomUUID().toString();
        customerList.setCustomerId(list);

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String text = date.format(formatter);
        LocalDate parsedDate = LocalDate.parse(text, formatter);

        return customerListRepository.save(customerList);
    }

    @Override
    public List<ICustomerListProj> getCustomerById(String id) {
        return customerListRepository.findByApplicationId(id);
    }

    @Override
    public CustomerList updateCustomer(CustomerList customerList) {
        return customerListRepository.save(customerList);
    }

    @Override
    public void deleteCustomer(String id) {
        customerListRepository.deleteById(id);
    }
}
