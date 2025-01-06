package com.ems.sow.repositories;

import com.ems.sow.model.ApplicationList;
import com.ems.sow.projection.IApplicationListProj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationListRepository extends JpaRepository<ApplicationList, String> {

    @Query(value = "SELECT \n" +
            "    al.application_id AS applicationId, \n" +
            "    al.application_name AS applicationName, \n" +
            "    al.description AS description, \n" +
            "    al.status AS status, \n" +
            "    COUNT(cd.customer_id) AS activeCustomerCount\n" +
            "FROM \n" +
            "    application_lists al \n" +
            "LEFT JOIN \n" +
            "    customer_details cd \n" +
            "ON \n" +
            "    al.application_id = cd.application_id\n" +
            "GROUP BY \n" +
            "    al.application_id, \n" +
            "    al.application_name, \n" +
            "    al.description, \n" +
            "    al.status \n" +
            "Order by al.application_name", nativeQuery = true)
    List<IApplicationListProj> getActiveCustomer();

    List<ApplicationList> findByUserName(String userName);
}
