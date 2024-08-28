package com.ems.sow.services.implementation;

import com.ems.sow.entities.CustomerList;
import com.ems.sow.repositories.CustomerListRepository;
import com.ems.sow.services.CustomerListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Customer List Implementation
 */

@Service
public class CustomerListImplementation implements CustomerListService {

    @Autowired
    private CustomerListRepository customerListRepository;

    @Override
    public List<CustomerList> getAllCustomersList() {
        return customerListRepository.findAll();
    }

    /**
     * @param customerList
     * @return
     */
    @Override
    public CustomerList createCustomer(CustomerList customerList) {
        return customerListRepository.save(customerList);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<List<CustomerList>> getCustomerById(Integer id) {
        return customerListRepository.findByApplicationId(id);
    }
}
