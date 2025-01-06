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
            "    cd.customer_id AS customerId,\n" +
            "    cd.customer_name AS customerName,\n" +
            "    cd.address AS address,\n" +
            "    cd.contact_person AS contactPerson,\n" +
            "    cd.sub_start_date AS subStartDate,\n" +
            "    cd.sub_end_date AS subEndDate,\n" +
            "    cd.device_capping AS deviceCapping,\n" +
            "    cd.site_capping AS siteCapping,\n" +
            "    COUNT(DISTINCT dd.rtu_id) AS deviceCount, \n" +
            "    COUNT(DISTINCT sd.site_id) AS siteCount,\n" +
            "    COUNT(DISTINCT ud.user_id) AS userCount\n" +
            "FROM \n" +
            "    customer_details cd\n" +
            "LEFT JOIN rtu_details dd ON cd.customer_id = dd.customer_id\n" +
            "LEFT JOIN site_details sd ON cd.customer_id = sd.customer_id\n" +
            "LEFT JOIN user_details ud ON cd.customer_id = ud.customer_id\n" +
            "WHERE \n" +
            "    cd.application_id = ?1\n" +
            "GROUP BY \n" +
            "    cd.customer_id, \n" +
            "    cd.customer_name, \n" +
            "    cd.address, \n" +
            "    cd.contact_person, \n" +
            "    cd.sub_start_date, \n" +
            "    cd.sub_end_date, \n" +
            "    cd.device_capping, \n" +
            "    cd.site_capping\n" +
            "ORDER BY \n" +
            "    cd.customer_name ASC;\n", nativeQuery = true)
    List<ICustomerListProj> findByAppId(String id);

    @Query(value =
            "SELECT \n" +
                    "    cd.customer_id AS customerId, \n" +
                    "    cd.application_id AS applicationId,\n" +
                    "    cd.customer_name AS customerName,\n" +
                    "    cd.address AS address, \n" +
                    "    cd.contact_person AS contactPerson, \n" +
                    "    cd.sub_start_date AS subStartDate, \n" +
                    "    cd.sub_end_date AS subEndDate,\n" +
                    "    COALESCE(dd.deviceCount, 0) AS deviceCount,\n" +
                    "    COALESCE(sd.siteCount, 0) AS siteCount,\n" +
                    "    COALESCE(ud.userCount, 0) AS userCount\n" +
                    "FROM customer_details cd\n" +
                    "LEFT JOIN (\n" +
                    "    SELECT customer_id, COUNT(DISTINCT rtu_id) AS deviceCount\n" +
                    "    FROM rtu_details\n" +
                    "    GROUP BY customer_id\n" +
                    ") dd ON cd.customer_id = dd.customer_id\n" +
                    "LEFT JOIN (\n" +
                    "    SELECT customer_id, COUNT(DISTINCT site_id) AS siteCount\n" +
                    "    FROM site_details\n" +
                    "    GROUP BY customer_id\n" +
                    ") sd ON cd.customer_id = sd.customer_id\n" +
                    "LEFT JOIN (\n" +
                    "    SELECT customer_id, COUNT(DISTINCT user_id) AS userCount\n" +
                    "    FROM user_details\n" +
                    "    GROUP BY customer_id\n" +
                    ") ud ON cd.customer_id = ud.customer_id\n" +
                    "ORDER BY cd.customer_name ASC;\n", nativeQuery = true)
    List<ICustomerListProj> findAllCustomer();

    List<CustomerList> findByUserName(String username);

    List<CustomerList> findByCustomerId(String id);
}
