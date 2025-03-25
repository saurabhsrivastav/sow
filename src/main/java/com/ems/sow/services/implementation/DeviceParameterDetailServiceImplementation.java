package com.ems.sow.services.implementation;

import com.ems.sow.projection.StreamDataProjection;
import com.ems.sow.repositories.DeviceParameterDetailRepository;
import com.ems.sow.services.DeviceParameterDetailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class DeviceParameterDetailServiceImplementation implements DeviceParameterDetailService {

    private final DeviceParameterDetailRepository deviceParameterDetailRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public DeviceParameterDetailServiceImplementation(DeviceParameterDetailRepository deviceParameterDetailRepository, ObjectMapper objectMapper) {
        this.deviceParameterDetailRepository = deviceParameterDetailRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public Page<Map> getJsonDataAsMap(String serialNumber, String deviceModbus, Pageable pageable) {
        Page<StreamDataProjection> jsonDataPage = deviceParameterDetailRepository.findByOsdValue(serialNumber, deviceModbus, pageable);
        return jsonDataPage.map(streamData -> objectMapper.convertValue(streamData, Map.class));
    }

    public List<Map> getFilteredData(String serialNumber, String deviceModbus, String filterType, LocalDate customStartDate, LocalDate customEndDate) {
        LocalDateTime startDate;
        LocalDateTime endDate = LocalDateTime.now(); // Default to current time

        switch (filterType != null ? filterType.toLowerCase() : "") {
            case "today":
                startDate = LocalDate.now().atStartOfDay(); // Start of today
                break;
            case "month":
                startDate = LocalDate.now().minusMonths(1).withDayOfMonth(1).atStartOfDay(); // Start of last month
                endDate = LocalDate.now().withDayOfMonth(1).minusDays(1).atTime(23, 59, 59); // End of last month
                break;
            case "custom":
                if (customStartDate == null || customEndDate == null) {
                    throw new IllegalArgumentException("Custom date range must be provided.");
                }
                startDate = customStartDate.atStartOfDay();
                endDate = customEndDate.atTime(23, 59, 59);
                break;
            default:
                throw new IllegalArgumentException("Invalid filter type.");
        }

        // Fetch data
        List<StreamDataProjection> jsonDataList = deviceParameterDetailRepository.findFilteredData(serialNumber, deviceModbus, startDate, endDate);

        return jsonDataList.stream()
                .map(streamData -> objectMapper.convertValue(streamData, Map.class))
                .toList();
    }

}

