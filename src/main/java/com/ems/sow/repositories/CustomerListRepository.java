package com.ems.sow.repositories;

import com.ems.sow.model.CustomerList;
import com.ems.sow.projection.ICustomerListProj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerListRepository extends JpaRepository<CustomerList, String> {

    @Query(value = "SELECT \n" +
            "    cd.customer_id AS customerId, \n" +
            "    cd.customer_name AS customerName, \n" +
            "    cd.address AS address, \n" +
            "    cd.contact_person AS contactPerson, \n" +
            "    cd.sub_start_date AS subscriptionStartDate, \n" +
            "    cd.sub_end_date AS subscriptionEndDate,\n" +
            "    (\n" +
            "        SELECT COUNT(DISTINCT dd.device_id) \n" +
            "        FROM device_details dd \n" +
            "        WHERE dd.customer_id = cd.customer_id\n" +
            "    ) AS deviceCount, \n" +
            "    (\n" +
            "        SELECT COUNT(DISTINCT sd.site_id) \n" +
            "        FROM site_details sd \n" +
            "        WHERE sd.customer_id = cd.customer_id\n" +
            "    ) AS siteCount\n" +
            "FROM \n" +
            "    customer_details cd \n" +
            "JOIN \n" +
            "    application_lists ap ON cd.application_id = ap.application_id\n" +
            "WHERE \n" +
            "    ap.application_id = ?1",
            nativeQuery = true)
    List<ICustomerListProj> findByApplicationId(String id);

    @Query(value = "SELECT \n" +
            "       cd.customer_id AS customerId, \n" +
            "       cd.customer_name AS customerName, \n" +
            "       cd.address AS address, \n" +
            "       cd.contact_person AS contactPerson, \n" +
            "       cd.sub_start_date AS subscriptionStartDate, \n" +
            "       cd.sub_end_date AS subscriptionEndDate,\n" +
            "       (\n" +
            "        SELECT COUNT(DISTINCT dd.device_id) \n" +
            "        FROM device_details dd \n" +
            "        WHERE dd.customer_id = cd.customer_id\n" +
            "        ) AS deviceCount, \n" +
            "        (\n" +
            "         SELECT COUNT(DISTINCT sd.site_id) \n" +
            "         FROM site_details sd \n" +
            "         WHERE sd.customer_id = cd.customer_id\n" +
            "        ) AS siteCount,\n" +
            "        (\n" +
            "        SELECT ap.application_name \n" +
            "        FROM application_lists ap \n" +
            "        WHERE ap.application_id = cd.application_id\n" +
            "        ) AS applicationLists\n" +
            "FROM \n" +
            "     customer_details cd", nativeQuery = true)
    List<ICustomerListProj> getAllCustomerDetails();
}
