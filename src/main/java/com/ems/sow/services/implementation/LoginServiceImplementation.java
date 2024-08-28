package com.ems.sow.services.implementation;

import com.ems.sow.entities.LoginDetails;
import com.ems.sow.repositories.LoginRepository;
import com.ems.sow.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImplementation implements LoginService {

    @Autowired
    private LoginRepository loginRepository;

    /**
     * @param pwd
     * @return
     */
    @Override
    public LoginDetails getUser(String pwd) {
        return loginRepository.findByPassword(pwd);
    }
}
