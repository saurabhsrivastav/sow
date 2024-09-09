package com.ems.sow.services;

import com.ems.sow.model.CustomerList;
import com.ems.sow.projection.ICustomerListProj;

import java.util.List;

public interface CustomerListService {

    public List<ICustomerListProj> getAllCustomersList();

    public CustomerList createCustomer(CustomerList customerList);

    public List<ICustomerListProj> getCustomerById(String id);

    CustomerList updateCustomer(CustomerList customerList);

    void deleteCustomer(String id);
}
