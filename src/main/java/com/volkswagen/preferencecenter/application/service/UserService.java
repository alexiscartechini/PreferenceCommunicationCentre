package com.volkswagen.preferencecenter.application.service;

import com.volkswagen.preferencecenter.application.exception.EmailAlreadyExistsException;
import com.volkswagen.preferencecenter.domain.model.User;
import com.volkswagen.preferencecenter.domain.port.UserPersistencePort;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserPersistencePort userPersistencePort;

    public UserService(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    public boolean isUniqueEmail(String email) {
        return userPersistencePort.findByEmail(email).isEmpty();
    }

    public User createUser(String email) {
        if (!isUniqueEmail(email)) throw new EmailAlreadyExistsException(email + " already exists.");
        User user = new User(email);
        userPersistencePort.save(user);
        return user;
    }
}