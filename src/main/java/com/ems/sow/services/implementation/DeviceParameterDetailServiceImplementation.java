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
    private ObjectMapper objectMapper; // Provided by Spring Boot by default


    @Override
    public List<Map> getJsonDataAsMap(String serialNumber, String deviceModbus) throws JsonProcessingException {
        // Fetch the data
        List<StreamDataProjection> jsonDataList = deviceParameterDetailRepository.findByOsdValue(serialNumber, deviceModbus);

        // Convert each record to a map
        ObjectMapper objectMapper = new ObjectMapper();
        return jsonDataList.stream()
                .map(streamData -> objectMapper.convertValue(streamData, Map.class))
                .toList();
    }

}

