package com.ems.sow.services.implementation;

import com.ems.sow.model.SiteDetails;
import com.ems.sow.projection.ISiteDetailsProj;
import com.ems.sow.repositories.SiteRepository;
import com.ems.sow.services.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SiteDetailImplementation implements SiteService {

    @Autowired
    private SiteRepository siteRepository;

    public List<SiteDetails> getAllSitesList() {
        return siteRepository.findAll();
    }

    @Override
    public List<SiteDetails> getSiteByCustomerId(String id) {
        return siteRepository.findByCustomerId(id);
    }


    @Override
    public SiteDetails createSite(SiteDetails siteDetails) {
        String siteId = UUID.randomUUID().toString();
        siteDetails.setSiteId(siteId);
        return siteRepository.save(siteDetails);
    }

    @Override
    public List<ISiteDetailsProj> getSiteById(String id) {
        return siteRepository.findBySiteIdDetails(id);
    }
}
