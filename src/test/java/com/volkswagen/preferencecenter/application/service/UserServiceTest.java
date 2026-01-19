package com.volkswagen.preferencecenter.application.service;

import com.volkswagen.preferencecenter.domain.model.User;
import com.volkswagen.preferencecenter.domain.port.UserPersistencePort;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private static final String NOT_UNIQUE_EMAIL = "not_unique_email@email.com";
    private static final String UNIQUE_EMAIL = "unique_email@email.com";
    private final UserPersistencePort userPersistencePort = mock(UserPersistencePort.class);
    private final UserService userService = new UserService(userPersistencePort);

    @Test
    void shouldReturnTrueIfEmailIsUnique(){
        when(userPersistencePort.findByEmail(UNIQUE_EMAIL)).thenReturn(Optional.empty());
        assertTrue(userService.isUniqueEmail(UNIQUE_EMAIL));
    }

    @Test
    void shouldReturnFalseIfEmailIsNotUnique(){
        when(userPersistencePort.findByEmail(NOT_UNIQUE_EMAIL)).thenReturn(Optional.of(new User(NOT_UNIQUE_EMAIL)));
        assertFalse(userService.isUniqueEmail(NOT_UNIQUE_EMAIL));
    }
}