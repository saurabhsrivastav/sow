package com.ems.sow.services.implementation;

import com.ems.sow.model.AlertsData;
import com.ems.sow.repositories.AlertListRepository;
import com.ems.sow.services.AlertListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AlertListImplementation implements AlertListService {

    private static final Logger logger = LoggerFactory.getLogger(AlertListImplementation.class);

    @Autowired
    private AlertListRepository alertListRepository;

    @Override
    public List<AlertsData> getAlertBySerialNumberAndOSD(String osd, String mdbid) {
        logger.info("Fetching alerts for serialNumber={} and deviceModbus={}", osd, mdbid);
        try {
            List<AlertsData> alertList = alertListRepository.findByOsdAndMdbid(osd, mdbid);

            if (alertList.isEmpty()) {
                logger.info("No alerts found for serialNumber={} and deviceModbus={}", osd, mdbid);
                return Collections.emptyList();
            }
            logger.info("Found {} alert(s) for serialNumber={} and deviceModbus={}", alertList.size(), osd, mdbid);
            return alertList;
        } catch (Exception ex) {
            logger.error("Error fetching alerts for serialNumber={} and deviceModbus={}: {}", osd, mdbid, ex.getMessage());
            throw ex;
        }
    }

    /**
     * @param osd
     * @param mdbid
     * @return
     */
    @Override
    public List<AlertsData> getAlertsByOsdAndMdbid(String osd, String mdbid) {
        return alertListRepository.findByOsdAndMdbid(osd, mdbid);
    }
}
