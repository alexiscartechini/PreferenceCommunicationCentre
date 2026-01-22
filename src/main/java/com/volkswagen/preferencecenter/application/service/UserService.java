package com.volkswagen.preferencecenter.application.service;

import com.volkswagen.preferencecenter.application.exception.EmailAlreadyExistsException;
import com.volkswagen.preferencecenter.application.exception.UserNotFoundException;
import com.volkswagen.preferencecenter.domain.model.Consent;
import com.volkswagen.preferencecenter.domain.model.User;
import com.volkswagen.preferencecenter.domain.port.ConsentRepositoryPort;
import com.volkswagen.preferencecenter.domain.port.UserRepositoryPort;
import com.volkswagen.preferencecenter.dto.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepositoryPort userRepositoryPort;
    private final ConsentRepositoryPort consentRepositoryPort;

    public UserService(UserRepositoryPort userRepositoryPort, ConsentRepositoryPort consentRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.consentRepositoryPort = consentRepositoryPort;
    }

    public boolean isUniqueEmail(String email) {
        return userRepositoryPort.findByEmail(email).isEmpty();
    }

    public User createUser(String email) {
        if (!isUniqueEmail(email)) throw new EmailAlreadyExistsException(email + " already exists.");
        User user = new User(email);
        userRepositoryPort.save(user);
        return user;
    }

    public UserResponse getUserWithCurrentConsents(UUID id) {
        User user = userRepositoryPort.findUserById(id).orElseThrow(() -> new UserNotFoundException("User not found."));
        List<Consent> consents = consentRepositoryPort.getConsentsByUserId(id);
        return UserResponse.from(user, consents);
    }

    public void deleteUser(UUID id) {
        userRepositoryPort.deleteById(id);
    }

    @Transactional
    public void updateEmail(UUID id, String email) {
        User user = userRepositoryPort.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found."));

        user.changeEmail(email);
    }
}