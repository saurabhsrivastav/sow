package com.ems.sow.repositories;

import com.ems.sow.model.SiteDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiteRepository extends JpaRepository<SiteDetails, String> {


    List<SiteDetails> findByCustomerId(String id);

    List<SiteDetails> findBySiteName(String name);
}
