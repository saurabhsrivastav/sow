package com.ems.sow.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import java.util.Map;

public interface DeviceParameterDetailService {


    List<Map> getJsonDataAsMap(String osd, String mdbid) throws JsonProcessingException;
}
