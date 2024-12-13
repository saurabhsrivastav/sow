package com.ems.sow.services;

import com.ems.sow.model.User;

public interface UserService {

//    User findByPasscode(String passcode);

    User register(User user);

    User findByEmailAndPassword(String email, String password);
}
