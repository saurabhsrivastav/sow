package com.ems.sow.services.implementation;

import com.ems.sow.model.StreamData;
import com.ems.sow.projection.StreamDataProjection;
import com.ems.sow.repositories.DeviceParameterDetailRepository;
import com.ems.sow.services.DeviceParameterDetailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DeviceParameterDetailServiceImplementation implements DeviceParameterDetailService {

    @Autowired
    private DeviceParameterDetailRepository deviceParameterDetailRepository;

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public Page<Map> getJsonDataAsMap(String serialNumber, String deviceModbus, Pageable pageable) {
        // Fetch the paginated data
        Page<StreamDataProjection> jsonDataPage = deviceParameterDetailRepository.findByOsdValue(serialNumber, deviceModbus, pageable);

        // Convert each record to a map and return a paginated response
        return jsonDataPage.map(streamData -> objectMapper.convertValue(streamData, Map.class));
    }
}

