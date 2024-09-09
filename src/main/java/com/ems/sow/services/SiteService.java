package com.ems.sow.services;

import com.ems.sow.model.SiteDetails;

import java.util.List;

public interface SiteService {

    List<SiteDetails> getAllSitesList();
    List<SiteDetails> getSiteById(String id);

    SiteDetails createSite(SiteDetails siteDetails);
}
