package com.ems.sow.services.implementation;

import com.ems.sow.model.CustomerList;
import com.ems.sow.repositories.CustomerListRepository;
import com.ems.sow.services.CustomerListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        String list = UUID.randomUUID().toString();
        customerList.setCustomerId(list);

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String text = date.format(formatter);
        LocalDate parsedDate = LocalDate.parse(text, formatter);

        return customerListRepository.save(customerList);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<List<CustomerList>> getCustomerById(Integer id) {
       // return customerListRepository.findByApplicationId(id);
        return null;
    }
}
