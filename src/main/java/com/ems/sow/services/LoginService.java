package com.ems.sow.services;

import com.ems.sow.entities.LoginDetails;

import java.util.Optional;

public interface LoginService {

    LoginDetails getUser(String pwd);
}
