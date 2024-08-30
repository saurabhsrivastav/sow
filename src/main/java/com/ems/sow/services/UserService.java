package com.ems.sow.services;

import com.ems.sow.model.User;

public interface UserService {

    User findByPasscode(String passcode);
    User addUser(User user);

}
