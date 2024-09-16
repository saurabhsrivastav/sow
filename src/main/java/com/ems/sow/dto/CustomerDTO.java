package com.ems.sow.dto;

import com.ems.sow.model.CustomerList;
import com.ems.sow.projection.ICustomerListProj;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

        private List<CustomerList> customers;
        private List<ICustomerListProj> deviceAndSiteData;

}
