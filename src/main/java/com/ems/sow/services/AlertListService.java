package com.ems.sow.services;

import com.ems.sow.model.AlertsData;

import java.util.List;

public interface AlertListService {

       List<AlertsData> getAlertBySerialNumberAndOSD(String serialNumber, String deviceModbus);

       List<AlertsData> getAlertsByOsdAndMdbid(String osd, String mdbid);
}
