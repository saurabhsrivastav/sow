package com.ems.sow.services;

import com.ems.sow.model.CustomerList;
import com.ems.sow.projection.ICustomerListProj;
import java.io.IOException;
import java.util.List;

public interface CustomerListService {

    List<CustomerList> getAllCustomers();

    List<CustomerList> getAllCustomers(String id);

    CustomerList createCustomer(CustomerList customerList) throws IOException;

    CustomerList updateCustomer(CustomerList customerList);

    void deleteCustomer(String id);

    List<ICustomerListProj> getDeviceAndSite(String id);
}
