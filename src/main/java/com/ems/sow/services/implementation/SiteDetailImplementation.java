package com.ems.sow.services.implementation;

import com.ems.sow.model.SiteDetails;
import com.ems.sow.projection.ISiteDetailsProj;
import com.ems.sow.repositories.SiteRepository;
import com.ems.sow.services.SiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SiteDetailImplementation implements SiteService {

    Logger logger = LoggerFactory.getLogger(SiteDetailImplementation.class);

    @Autowired
    private SiteRepository siteRepository;

    public List<SiteDetails> getAllSitesList() {
        logger.info("All Site Details List");
        return siteRepository.findAll();
    }

    @Override
    public List<SiteDetails> getSiteByCustomerId(String id) {
        logger.info("Site Details List by Customer Id");
        return siteRepository.findByCustomerId(id);
    }


    @Override
    public SiteDetails createSite(SiteDetails siteDetails) {
        if (siteRepository.existsBySiteName(siteDetails.getSiteName())) {
            return null;
        }
        siteDetails.setSiteId(UUID.randomUUID().toString());
        return siteRepository.save(siteDetails);
    }

    @Override
    public List<ISiteDetailsProj> getSiteById(String id) {
        logger.info("Site Details List by Site Id");
        return siteRepository.findBySiteIdDetails(id);
    }

}
