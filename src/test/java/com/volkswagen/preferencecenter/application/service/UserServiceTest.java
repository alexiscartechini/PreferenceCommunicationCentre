package com.volkswagen.preferencecenter.application.service;

import com.volkswagen.preferencecenter.domain.port.UserPersistencePort;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {

    private final UserPersistencePort userPersistencePort = mock(UserPersistencePort.class);
    private final UserService userService = new UserService(userPersistencePort);

    @Test
    void shouldReturnTrueIfEmailIsUnique(){
        when(userPersistencePort.findByEmail("unique_email@email.com")).thenReturn(Optional.empty());
        assertTrue(userService.isUniqueEmail("unique_email@email.com"));
    }
}