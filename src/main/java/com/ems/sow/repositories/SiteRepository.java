package com.ems.sow.repositories;

import com.ems.sow.entities.SiteDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepository extends JpaRepository<SiteDetails, Long> {


}
