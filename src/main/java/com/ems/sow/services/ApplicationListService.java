package com.ems.sow.services;

import com.ems.sow.entities.ApplicationList;
import com.ems.sow.projection.IApplicationListProj;

import java.util.List;


public interface ApplicationListService {

    List<IApplicationListProj> getActiveCustomerCount();

}
