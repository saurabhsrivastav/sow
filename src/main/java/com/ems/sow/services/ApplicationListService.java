package com.ems.sow.services;

import com.ems.sow.model.ApplicationList;
import com.ems.sow.projection.IApplicationListProj;

import java.util.List;


public interface ApplicationListService {

    List<ApplicationList> getAllApplicationList();
    ApplicationList createApplication(ApplicationList list);
    List<IApplicationListProj> getActiveCustomerCount();
    ApplicationList updateStatus(ApplicationList list);

}
