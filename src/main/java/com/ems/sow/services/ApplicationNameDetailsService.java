package com.ems.sow.services;

import com.ems.sow.entities.ApplicationList;
import com.ems.sow.entities.ApplicationNameDetails;
import com.ems.sow.repositories.ApplicationNameDetailsRepository;

import java.util.List;

public interface ApplicationNameDetailsService {

    public List<ApplicationNameDetails> getApplicationDetails();
}
