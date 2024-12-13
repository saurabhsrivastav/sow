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

    /**
     * @param id
     * @param deviceModbus
     * @return
     */
    @Override
    public List<StreamData> getDeviceParameterDetails(String id, String deviceModbus) {
        return List.of();
    }

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



//    @Override
//    public List<StreamDataProjection> getJsonDataAsMap(String serialNumber, String deviceModbus) throws JsonProcessingException {
//        List<StreamDataProjection> jsonDataList = deviceParameterDetailRepository.findByOsdValue(serialNumber, deviceModbus);
//
//        if (!jsonDataList.isEmpty()) {
//            StreamDataProjection streamData = jsonDataList.get(0); // Get the first record
//            ObjectMapper objectMapper = new ObjectMapper();
//            return objectMapper.convertValue(streamData, Map.class);
//        } else {
//            return null; // Or throw an exception
//        }
//    }




//   @Override
//   public List<Map> getJsonDataAsMap(String osd, String mdbid, Pageable pageable) throws JsonProcessingException {
//       Page<StreamDataProjection> jsonDataList = deviceParameterDetailRepository.findByOsdValueWithPagination(serialNumber, deviceModbus, pageable);

//        List<StreamDataProjection> content = jsonDataList.getContent();
//        if (!content.isEmpty()) {
//            ObjectMapper objectMapper = new ObjectMapper();
//            return content.stream()
//                    .map(streamData -> objectMapper.convertValue(streamData, Map.class))
//                    .toList();
//        } else {
//            return null;
//        }
        // Fetch paginated data
//        Page<StreamDataProjection> jsonDataPage = deviceParameterDetailRepository.findByOsdValueWithPagination(serialNumber, deviceModbus, pageable);
//
//        List<StreamDataProjection> content = jsonDataPage.getContent();
//
//        // Convert content to a list of maps
//        List<Map<String, Object>> convertedContent = jsonDataPage.getContent().stream()
//                .map(streamData -> {
//                    ObjectMapper objectMapper = new ObjectMapper();
//                    return objectMapper.convertValue(streamData, Map.class);
//                })
//                .toList();
//
//        // Return as a Page<Map<String, Object>> while preserving pagination metadata
//        return new PageImpl<>(convertedContent, pageable, jsonDataPage.getTotalElements());
    }
//}
