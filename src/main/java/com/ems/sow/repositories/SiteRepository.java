package com.ems.sow.repositories;

import com.ems.sow.model.SiteDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepository extends JpaRepository<SiteDetails, String> {


}
