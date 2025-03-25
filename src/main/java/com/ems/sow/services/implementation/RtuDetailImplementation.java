package com.ems.sow.services.implementation;

import com.ems.sow.model.RtuDetails;
import com.ems.sow.projection.IRtuDetailList;
import com.ems.sow.projection.IRtuListProj;
import com.ems.sow.repositories.RtuDetailRepository;
import com.ems.sow.services.RtuDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RtuDetailImplementation implements RtuDetailService {

    private final Logger logger = LoggerFactory.getLogger(RtuDetailImplementation.class);

    @Autowired
    private RtuDetailRepository repository;

    @Override
    public List<RtuDetails> getAllRtuDetails() {
        logger.info("Controller in get all RTU details : ");
        return repository.findAll();
    }

    @Override
    public RtuDetails saveRtu(RtuDetails rtuDetails) {
        logger.info("Controller in save RTU : ");
        List<RtuDetails> response =
                findByCustomerIdAndSerialNumber(
                        rtuDetails.getCustomerId(), rtuDetails.getSerialNumber());

        if(!response.isEmpty()) {
            RtuDetails existingDevice = response.get(0);
            existingDevice.setStatus(true);
            return existingDevice;
        } else {
            rtuDetails.setRtuId(UUID.randomUUID().toString());
            return repository.save(rtuDetails);
        }
    }

    @Override
    public Optional<List<IRtuDetailList>> getRtu(String id) {
        logger.info("Controller in get RTU");
        return repository.findRtuDetailsByCustomerId(id);
    }

    @Override
    public List<IRtuListProj> findRtuStatus(String id) {
        logger.info("Controller in find RTU Status : ");
        return repository.findRtuStatus(id);
    }

    @Override
    public RtuDetails updateRtuDetails(RtuDetails rtuDetails) {
        logger.info("Controller in update RTU details : " + rtuDetails.getSerialNumber());
        final List<RtuDetails> responseByRtuName =
                repository.findBySerialNumber(rtuDetails.getSerialNumber());
        for (RtuDetails list : responseByRtuName) {
            list.setStatus(true);
            list.setSiteId(rtuDetails.getSiteId());
            repository.save(list);
        }
        return rtuDetails;
    }

    @Override
    public List<RtuDetails> getRtuByCustomerId(String id) {
        logger.info("Controller in get RTU by Customer Id : ");
        return repository.findAllByCustomerIdAndStatus(id, false);
    }

    @Override
    public List<RtuDetails> uninstallRtu(RtuDetails rtuDetails) {
        logger.info("Controller in uninstall RTU : ");

        List<RtuDetails> responseByDeviceName = repository.findByRtuId(rtuDetails.getRtuId());
        if (responseByDeviceName.isEmpty()) {
            logger.warn("No RTU found with ID: " + rtuDetails.getRtuId());
            return Collections.emptyList();
        }
        responseByDeviceName.forEach(list -> list.setStatus(rtuDetails.isStatus()));

        return repository.saveAll(responseByDeviceName);
    }

    @Override
    public List<RtuDetails> findRtuByRtuId(String rtuId) {
        logger.info("Controller in Find Rtu by RTU Id: ");
        return repository.findByRtuId(rtuId);
    }

    @Override
    public List<RtuDetails> findSerialNumbersByCustomerId(String customerId) {
        logger.info("Controller in find Serial Numbers By CustomerId: ");
        return repository.findByCustomerId(customerId);
    }

    @Override
    public List<RtuDetails> findByCustomerIdAndSerialNumber(String customerId, String SerialNumber) {
        logger.info("Controller in find By Customer Id And Serial Number: ");
        return repository.findByCustomerIdAndSerialNumber(customerId, SerialNumber);
    }

    @Override
    public RtuDetails updateDeviceId(String serialNumber, String deviceId) {
        List<RtuDetails> bySerialNumber = repository.findBySerialNumber(serialNumber);

        for (RtuDetails list : bySerialNumber) {
            list.setDevice(deviceId);
            repository.save(list);
        }
        return null;
    }
}