package com.ems.sow.services;

import com.ems.sow.model.AlertList;

import java.util.List;

public interface AlertListService {

    List<AlertList> getAlertByCustomerId(String id);

    List<AlertList> getAlertByDeviceId(String id);
}
