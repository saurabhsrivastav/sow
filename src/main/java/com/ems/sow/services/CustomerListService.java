package com.ems.sow.services;

import com.ems.sow.entities.CustomerList;

import java.util.List;
import java.util.Optional;

public interface CustomerListService {

    /**
     * Get All Customer List
     *
     * @return
     */

    public List<CustomerList> getAllCustomersList();

    public CustomerList createCustomer(CustomerList customerList);

    public Optional<List<CustomerList>> getCustomerById(Integer id);

}
