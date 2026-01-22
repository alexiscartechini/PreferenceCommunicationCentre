package com.volkswagen.preferencecenter.domain.port;

import com.volkswagen.preferencecenter.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort {

    Optional<User> findByEmail(String email);

    void save(User user);

    Optional<User> findUserById(UUID id);

    void deleteById(UUID id);
}