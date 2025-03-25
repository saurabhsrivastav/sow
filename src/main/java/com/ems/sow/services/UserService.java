package com.ems.sow.services;

import com.ems.sow.model.User;

public interface UserService {

    User register(User user);

    User findByUserNameAndPassword(String email, String password);
}
