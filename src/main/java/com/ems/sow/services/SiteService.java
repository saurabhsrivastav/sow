package com.ems.sow.services;

import com.ems.sow.model.SiteDetails;
import com.ems.sow.projection.ISiteDetailsProj;

import java.util.List;

public interface SiteService {

    List<SiteDetails> getAllSitesList();

    List<SiteDetails> getSiteByCustomerId(String id);

    SiteDetails createSite(SiteDetails siteDetails);

    List<ISiteDetailsProj> getSiteById(String Id);

}
