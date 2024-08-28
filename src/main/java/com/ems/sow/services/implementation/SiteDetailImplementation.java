package com.ems.sow.services.implementation;

import com.ems.sow.entities.CustomerList;
import com.ems.sow.entities.SiteDetails;
import com.ems.sow.repositories.SiteRepository;
import com.ems.sow.services.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteDetailImplementation implements SiteService {

    @Autowired
    private SiteRepository siteRepository;

    public List<SiteDetails> getAllSitesList() {
        return siteRepository.findAll();
    }
}
