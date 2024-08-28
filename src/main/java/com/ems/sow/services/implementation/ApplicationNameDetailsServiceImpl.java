package com.ems.sow.services.implementation;

import com.ems.sow.entities.ApplicationList;
import com.ems.sow.entities.ApplicationNameDetails;
import com.ems.sow.repositories.ApplicationNameDetailsRepository;
import com.ems.sow.services.ApplicationNameDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationNameDetailsServiceImpl implements ApplicationNameDetailsService {

    @Autowired
    private ApplicationNameDetailsRepository nameDetailsRepository;

    /**
     * @return
     */
    @Override
    public List<ApplicationNameDetails> getApplicationDetails() {
        return nameDetailsRepository.findAll();
    }
}
