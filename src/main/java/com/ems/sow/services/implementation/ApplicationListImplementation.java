package com.ems.sow.services.implementation;

import com.ems.sow.model.ApplicationList;
import com.ems.sow.projection.IApplicationListProj;
import com.ems.sow.repositories.ApplicationListRepository;
import com.ems.sow.services.ApplicationListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Application List Implementation
 */

@Service
public class ApplicationListImplementation implements ApplicationListService {

    @Autowired
    private ApplicationListRepository repository;

    @Override
    public List<ApplicationList> getAllApplicationList() {
        return repository.findAll();
    }

    @Override
    public ApplicationList createApplication(ApplicationList list) {
        String appId = UUID.randomUUID().toString();
        list.setApplicationId(appId);
        return repository.save(list);
    }

    @Override
    public List<IApplicationListProj> getActiveCustomerCount() {
        return repository.getActiveCustomerCount();
    }

    @Override
    public ApplicationList updateStatus(ApplicationList list) {
        return repository.save(list);
    }
}




