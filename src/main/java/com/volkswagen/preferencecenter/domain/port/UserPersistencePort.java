package com.volkswagen.preferencecenter.domain.port;

import com.volkswagen.preferencecenter.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserPersistencePort {

    Optional<User> findByEmail(String email);

    void save(User user);

    Optional<User> findUserById(UUID id);
}