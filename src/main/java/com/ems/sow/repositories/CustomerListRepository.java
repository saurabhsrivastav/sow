package com.ems.sow.repositories;

import com.ems.sow.model.CustomerList;
import com.ems.sow.projection.ICustomerListProj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerListRepository extends JpaRepository<CustomerList, String> {

    @Query(value =
            "SELECT " +
                    "    cd.customer_id AS customerId, " +
                    "    cd.application_id AS applicationId, " +
                    "    cd.customer_name AS customerName, " +
                    "    cd.address AS address, " +
                    "    cd.contact_person AS contactPerson, " +
                    "    cd.sub_start_date AS subStartDate, " +
                    "    cd.sub_end_date AS subEndDate, " +
                    "    (" +
                    "        SELECT COUNT(DISTINCT dd.device_id) " +
                    "        FROM device_details dd " +
                    "        WHERE dd.customer_id = cd.customer_id" +
                    "    ) AS deviceCount, " +
                    "    (" +
                    "        SELECT COUNT(DISTINCT sd.site_id) " +
                    "        FROM site_details sd " +
                    "        WHERE sd.customer_id = cd.customer_id" +
                    "    ) AS siteCount " +
                    "FROM " +
                    "    customer_details cd " +
                    "JOIN " +
                    "    application_lists ap ON cd.application_id = ap.application_id " +
                    "WHERE " +
                    "    ap.application_id = ?1",
            nativeQuery = true)
    List<ICustomerListProj> findByAppId(String id);

    List<CustomerList> findAllByCustomerId(String id);
}
