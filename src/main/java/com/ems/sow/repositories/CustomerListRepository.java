package com.ems.sow.repositories;

import com.ems.sow.entities.CustomerList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerListRepository extends JpaRepository<CustomerList, Integer> {

    Optional<List<CustomerList>> findByApplicationId(Integer id);
}
