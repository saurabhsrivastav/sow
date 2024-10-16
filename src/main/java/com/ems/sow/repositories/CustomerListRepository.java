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
            "SELECT \n" +
                    "    cd.customer_id AS customerId, \n" +
                    "    cd.application_id AS applicationId, \n" +
                    "    cd.customer_name AS customerName,\n" +
                    "    cd.address AS address, \n" +
                    "    cd.contact_person AS contactPerson,\n" +
                    "    cd.sub_start_date AS subStartDate, \n" +
                    "    cd.sub_end_date AS subEndDate,\n" +
                    "    cd.device_capping AS deviceCapping, \n" +
                    "    cd.site_capping AS siteCapping,\n" +
                    "    COUNT(DISTINCT dd.device_id) AS deviceCount,\n" +
                    "    COUNT(DISTINCT sd.site_id) AS siteCount\n" +
                    "FROM \n" +
                    "    customer_details cd\n" +
                    "JOIN \n" +
                    "    application_lists ap \n" +
                    "    ON cd.application_id = ap.application_id\n" +
                    "LEFT JOIN \n" +
                    "    device_details dd \n" +
                    "    ON cd.customer_id = dd.customer_id\n" +
                    "LEFT JOIN \n" +
                    "    site_details sd \n" +
                    "    ON cd.customer_id = sd.customer_id\n" +
                    "WHERE \n" +
                    "    ap.application_id = ?1\n" +
                    "GROUP BY \n" +
                    "    cd.customer_id, \n" +
                    "    cd.application_id, \n" +
                    "    cd.customer_name, \n" +
                    "    cd.address, \n" +
                    "    cd.contact_person, \n" +
                    "    cd.sub_start_date, \n" +
                    "    cd.sub_end_date\n" +
                    "ORDER BY \n" +
                    "    cd.customer_name ASC", nativeQuery = true)
    List<ICustomerListProj> findByAppId(String id);

    List<CustomerList> findByCustomerId(String id);


    @Query(value =
            "SELECT \n" +
                    "    cd.customer_id AS customerId,\n" +
                    "    cd.application_id AS applicationId,\n" +
                    "    cd.customer_name AS customerName,\n" +
                    "    cd.address AS address,\n" +
                    "    cd.contact_person AS contactPerson,\n" +
                    "    cd.sub_start_date AS subStartDate,\n" +
                    "    cd.sub_end_date AS subEndDate,\n" +
                    "    COUNT(DISTINCT dd.device_id) AS deviceCount,\n" +
                    "    COUNT(DISTINCT sd.site_id) AS siteCount\n" +
                    "FROM \n" +
                    "    customer_details cd\n" +
                    "LEFT JOIN \n" +
                    "    device_details dd ON cd.customer_id = dd.customer_id\n" +
                    "LEFT JOIN \n" +
                    "    site_details sd ON cd.customer_id = sd.customer_id\n" +
                    "GROUP BY \n" +
                    "    cd.customer_id, cd.application_id, cd.customer_name, cd.address, cd.contact_person, \n" +
                    "    cd.sub_start_date, cd.sub_end_date\n "  +
                    "ORDER BY \n" +
                    "    cd.customer_name ASC", nativeQuery = true)
    List<ICustomerListProj> findAllCustomer();

}
