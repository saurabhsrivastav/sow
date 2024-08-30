package com.ems.sow.services.implementation;

import com.ems.sow.model.User;
import com.ems.sow.repositories.UserRepository;
import com.ems.sow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    /**
     * @param passcode
     * @return
     */
    @Override
    public User findByPasscode(String passcode) {
        return repository.findByPasscode(passcode);
    }

    /**
     * @param user
     * @return
     */
    @Override
    public User addUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        return repository.save(user);
    }
}
