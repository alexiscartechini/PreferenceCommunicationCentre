package com.volkswagen.preferencecenter.application.service;

import com.volkswagen.preferencecenter.domain.model.Consent;
import com.volkswagen.preferencecenter.domain.model.ConsentEvent;
import com.volkswagen.preferencecenter.domain.model.User;
import com.volkswagen.preferencecenter.domain.port.ConsentPersistencePort;
import com.volkswagen.preferencecenter.domain.port.UserPersistencePort;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserService {

    private final UserPersistencePort userPersistencePort;
    private final ConsentPersistencePort consentPersistencePort;

    public UserService(UserPersistencePort userPersistencePort, ConsentPersistencePort consentPersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.consentPersistencePort = consentPersistencePort;
    }

    public boolean isUniqueEmail(String email) {
        return userPersistencePort.findByEmail(email).isEmpty();
    }

    public void chooseNotification(Consent consent) {
        consentPersistencePort.save(new ConsentEvent(consent, Instant.now()));
    }

    public void createUser(String email) {
        userPersistencePort.save(new User(email));
    }
}