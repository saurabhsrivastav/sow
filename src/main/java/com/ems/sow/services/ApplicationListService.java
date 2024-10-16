package com.ems.sow.services;

import com.ems.sow.model.ApplicationList;
import com.ems.sow.projection.IApplicationListProj;

import java.util.List;


public interface ApplicationListService {

    List<ApplicationList> getAllApplications();

    ApplicationList createNewApplication(ApplicationList list);

    List<IApplicationListProj> getActiveCustomer();

    ApplicationList updateStatus(ApplicationList list);
}
