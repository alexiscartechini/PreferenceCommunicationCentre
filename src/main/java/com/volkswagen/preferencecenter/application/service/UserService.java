package com.volkswagen.preferencecenter.application.service;

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
}