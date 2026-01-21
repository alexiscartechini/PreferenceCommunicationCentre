package com.volkswagen.preferencecenter.application.service;

import com.volkswagen.preferencecenter.application.exception.EmailAlreadyExistsException;
import com.volkswagen.preferencecenter.application.exception.UserNotFoundException;
import com.volkswagen.preferencecenter.domain.model.Consent;
import com.volkswagen.preferencecenter.domain.model.User;
import com.volkswagen.preferencecenter.domain.port.ConsentPersistencePort;
import com.volkswagen.preferencecenter.domain.port.UserPersistencePort;
import com.volkswagen.preferencecenter.dto.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public User createUser(String email) {
        if (!isUniqueEmail(email)) throw new EmailAlreadyExistsException(email + " already exists.");
        User user = new User(email);
        userPersistencePort.save(user);
        return user;
    }

    public UserResponse getUserWithCurrentConsents(UUID id) {
        User user = userPersistencePort.findUserById(id).orElseThrow(() -> new UserNotFoundException("User not found."));
        List<Consent> consents = consentPersistencePort.getConsentsByUserId(id);
        return UserResponse.from(user, consents);
    }
}