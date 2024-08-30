package com.ems.sow.repositories;

import com.ems.sow.model.ApplicationList;
import com.ems.sow.projection.IApplicationListProj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationListRepository extends JpaRepository<ApplicationList, String> {

    @Query(value = "select al.application_id as applicationID, al.application_name as applicationName, al.description as applicationDescription, al.status as status, count(*) as activeCustomerCount " +
            "from application_lists as al join customer_details as cd " +
            "on al.application_id = cd.application_id " +
            "group by al.application_id " +
            "ORDER BY al.application_id ASC ", nativeQuery = true)
    List<IApplicationListProj> getActiveCustomerCount();
}
