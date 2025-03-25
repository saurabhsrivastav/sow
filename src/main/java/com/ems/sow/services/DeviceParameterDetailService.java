package com.ems.sow.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface DeviceParameterDetailService {

    Page<Map> getJsonDataAsMap(String serialNumber, String deviceModbus, Pageable pageable);

    List<Map> getFilteredData(String serialNumber, String deviceModbus, String filterType, LocalDate customStartDate, LocalDate customEndDate);
}
