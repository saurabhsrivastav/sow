package com.ems.sow.repositories;

import com.ems.sow.model.CustomerList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerListRepository extends JpaRepository<CustomerList, String> {

   // Optional<List<CustomerList>> findByApplicationId(Integer id);
}
