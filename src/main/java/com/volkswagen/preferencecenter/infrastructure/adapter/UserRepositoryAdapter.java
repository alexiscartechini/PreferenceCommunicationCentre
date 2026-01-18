package com.volkswagen.preferencecenter.infrastructure.adapter;

import com.volkswagen.preferencecenter.domain.model.User;
import com.volkswagen.preferencecenter.domain.port.UserPersistencePort;
import com.volkswagen.preferencecenter.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryAdapter implements UserPersistencePort {

    private final UserRepository userRepository;

    public UserRepositoryAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}