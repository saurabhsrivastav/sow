package com.ems.sow.services;

import com.ems.sow.model.CustomerList;
import com.ems.sow.projection.ICustomerListProj;
import java.io.IOException;
import java.util.List;

public interface CustomerListService {

    List<ICustomerListProj> getAllCustomers();

    List<CustomerList> loadCustomerImage(String id);

    CustomerList createCustomer(CustomerList customerList) throws IOException;

    CustomerList updateCustomer(CustomerList customerList);

    void deleteCustomer(String id);

    List<ICustomerListProj> getCustomerByApplicationId(String id);
}
