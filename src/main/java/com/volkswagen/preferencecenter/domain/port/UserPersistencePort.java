package com.volkswagen.preferencecenter.domain.port;

import com.volkswagen.preferencecenter.domain.model.User;

import java.util.Optional;

public interface UserPersistencePort {

    Optional<User> findByEmail(String email);
    void save(User user);
    Optional<User> findUserById(String id);
}