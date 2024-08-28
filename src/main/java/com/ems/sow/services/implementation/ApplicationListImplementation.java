package com.ems.sow.services.implementation;

import com.ems.sow.dto.ApplicationListDTO;
import com.ems.sow.entities.ApplicationList;
import com.ems.sow.projection.IApplicationListProj;
import com.ems.sow.repositories.ApplicationListRepository;
import com.ems.sow.services.ApplicationListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Application List Implementation
 */

@Service
public class ApplicationListImplementation implements ApplicationListService {

    @Autowired
    private ApplicationListRepository repository;

    /**
     * @return
     */
    @Override
    public List<IApplicationListProj> getActiveCustomerCount() {
        return repository.getActiveCustomerCount();
    }
}
