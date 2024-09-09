package com.ems.sow.repositories;

import com.ems.sow.model.DeviceDetailList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceInfoRepository extends JpaRepository<DeviceDetailList, String> {

    List<DeviceDetailList> findByDeviceId(String id);
}
