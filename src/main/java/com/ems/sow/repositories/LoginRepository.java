package com.ems.sow.repositories;

import com.ems.sow.entities.LoginDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<LoginDetails, String> {

    LoginDetails findByPassword(String password);
}
