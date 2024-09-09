package com.ems.sow.services.implementation;

import com.ems.sow.model.AlertList;
import com.ems.sow.repositories.AlertListRepository;
import com.ems.sow.services.AlertListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Alert List Implementation
 */

@Service
public class AlertListImplementation implements AlertListService {

    @Autowired
    private AlertListRepository alertListRepository;

    /**
     * Get All Alert List
     *
     * @param id
     * @return
     */
    @Override
    public List<AlertList> getAlertByCustomerId(String id) {
        return alertListRepository.findByCustomerId(id);
    }
}
