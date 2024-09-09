package com.ems.sow.repositories;

import com.ems.sow.model.AlertList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertListRepository extends JpaRepository<AlertList, String> {

    List<AlertList> findByCustomerId(String id);
}
