package com.ems.sow.services.implementation;

import com.ems.sow.model.ApplicationList;
import com.ems.sow.projection.IApplicationListProj;
import com.ems.sow.repositories.ApplicationListRepository;
import com.ems.sow.services.ApplicationListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class ApplicationListImplementation implements ApplicationListService {

    private final Logger logger = LoggerFactory.getLogger(ApplicationListImplementation.class);

    @Autowired
    private ApplicationListRepository repository;

    @Override
    public ApplicationList createNewApplication(ApplicationList list) {
        logger.info("entered in create New Application()");
        String appId = UUID.randomUUID().toString();
        list.setApplicationId(appId);
        return repository.save(list);
    }

    @Override
    public List<ApplicationList> getAllApplications() {
        logger.info("entered in getAllApplications ()");
        return repository.findAll();
    }

    @Override
    public List<IApplicationListProj> getActiveCustomer() {
        logger.info("entered in getActiveCustomer ()");
        return repository.getActiveCustomer();
    }

    @Override
    public ApplicationList updateStatus(ApplicationList list) {
        logger.info("entered in updateStatus ()");
        return repository.save(list);
    }

    @Override
    public List<ApplicationList>  getApplicationByUserName(String userName) {
        return repository.findByUserName(userName);
    }
}




