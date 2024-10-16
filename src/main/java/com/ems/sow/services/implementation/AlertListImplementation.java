package com.ems.sow.services.implementation;

import com.ems.sow.controllers.AlertListController;
import com.ems.sow.model.AlertList;
import com.ems.sow.repositories.AlertListRepository;
import com.ems.sow.services.AlertListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class AlertListImplementation implements AlertListService {

    Logger logger = Logger.getLogger(AlertListController.class.getName());
    @Autowired
    private AlertListRepository alertListRepository;

    @Override
    public List<AlertList> getAlertByCustomerId(String id) {
        logger.info("entered in service implementation getAlertByCustomerId method <" + id + "> customer id");
        return alertListRepository.findByCustomerId(id);
    }

    @Override
    public List<AlertList> getAlertByDeviceId(String id) {
        logger.info("entered in service implementation getAlertByDeviceId method <" + id + "> device id");
        return alertListRepository.findByDeviceId(id);
    }
}
