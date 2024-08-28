package com.ems.sow.repositories;

import com.ems.sow.entities.AlertList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertListRepository extends JpaRepository<AlertList, Integer> {

    List<AlertList> findByCustomerId(Integer id);
}
