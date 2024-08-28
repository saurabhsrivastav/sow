package com.ems.sow.repositories;

import com.ems.sow.entities.ApplicationNameDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationNameDetailsRepository extends JpaRepository<ApplicationNameDetails, Integer> {

}
