package com.ems.sow.services.implementation;

import com.ems.sow.model.AlertList;
import com.ems.sow.repositories.AlertListRepository;
import com.ems.sow.services.AlertListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertListImplementation implements AlertListService {

    @Autowired
    private AlertListRepository alertListRepository;

    @Override
    public List<AlertList> getAlertByCustomerId(String id) {
        return alertListRepository.findByCustomerId(id);
    }

    @Override
    public List<AlertList> getAlertByDeviceId(String id) {
        return alertListRepository.findByDeviceId(id);
    }
}
