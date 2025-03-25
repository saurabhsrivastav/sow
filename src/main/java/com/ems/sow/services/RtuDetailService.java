package com.ems.sow.services;

import com.ems.sow.model.RtuDetails;
import com.ems.sow.projection.IRtuDetailList;
import com.ems.sow.projection.IRtuListProj;

import java.util.List;
import java.util.Optional;

public interface RtuDetailService {


    List<RtuDetails> getAllRtuDetails();

    RtuDetails saveRtu(RtuDetails rtuDetails);

    Optional<List<IRtuDetailList>> getRtu(String id);

    List<IRtuListProj> findRtuStatus(String id);

    RtuDetails updateRtuDetails(RtuDetails rtuDetails);

    List<RtuDetails> getRtuByCustomerId(String id);

    List<RtuDetails> uninstallRtu(RtuDetails rtuDetails);

    List<RtuDetails> findRtuByRtuId(String rtuId);

    List<RtuDetails> findSerialNumbersByCustomerId(String customerId);

    List<RtuDetails> findByCustomerIdAndSerialNumber(String customerId, String SerialNumber);

    RtuDetails updateDeviceId(String serialNumber, String deviceId);
}
