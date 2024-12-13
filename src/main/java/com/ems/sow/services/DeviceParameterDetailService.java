package com.ems.sow.services;

import com.ems.sow.model.StreamData;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface DeviceParameterDetailService {

    List<StreamData> getDeviceParameterDetails(String id, String deviceModbus);

    List<Map> getJsonDataAsMap(String osd, String mdbid) throws JsonProcessingException;
}
