package com.ems.sow.services;

import com.ems.sow.entities.CustomerList;
import com.ems.sow.entities.SiteDetails;
import com.ems.sow.repositories.SiteRepository;

import java.util.List;

public interface SiteService {

    public List<SiteDetails> getAllSitesList();

}
