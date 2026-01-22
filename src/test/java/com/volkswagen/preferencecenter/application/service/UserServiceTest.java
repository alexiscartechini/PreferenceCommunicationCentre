package com.volkswagen.preferencecenter.application.service;

import com.volkswagen.preferencecenter.application.exception.EmailAlreadyExistsException;
import com.volkswagen.preferencecenter.application.exception.UserNotFoundException;
import com.volkswagen.preferencecenter.domain.model.Consent;
import com.volkswagen.preferencecenter.domain.model.ConsentType;
import com.volkswagen.preferencecenter.domain.model.User;
import com.volkswagen.preferencecenter.domain.port.ConsentPersistencePort;
import com.volkswagen.preferencecenter.domain.port.UserPersistencePort;
import com.volkswagen.preferencecenter.dto.UserResponse;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private static final String NOT_UNIQUE_EMAIL = "not_unique_email@email.com";
    private static final String UNIQUE_EMAIL = "unique_email@email.com";
    private final UserPersistencePort userPersistencePort = mock(UserPersistencePort.class);
    private final ConsentPersistencePort consentPersistencePort = mock(ConsentPersistencePort.class);
    private final UserService userService = new UserService(userPersistencePort, consentPersistencePort);

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {
        when(userPersistencePort.findByEmail(NOT_UNIQUE_EMAIL)).thenReturn(Optional.of(new User(NOT_UNIQUE_EMAIL)));

        assertFalse(userService.isUniqueEmail(NOT_UNIQUE_EMAIL));
        assertThrows(EmailAlreadyExistsException.class, () -> userService.createUser(NOT_UNIQUE_EMAIL));
        verify(userPersistencePort, never()).save(any());
    }

    @Test
    void shouldCreateUser() {
        when(userPersistencePort.findByEmail(UNIQUE_EMAIL)).thenReturn(Optional.empty());

        User result = userService.createUser(UNIQUE_EMAIL);

        assertTrue(userService.isUniqueEmail(UNIQUE_EMAIL));
        assertNotNull(result);
        verify(userPersistencePort).save(any(User.class));
    }

    @Test
    void shouldReturnUserWithConsents() {
        UUID userId = UUID.randomUUID();
        User user = new User(UNIQUE_EMAIL);

        List<Consent> consents = List.of(
                new Consent(ConsentType.SMS_NOTIFICATIONS, userId, true),
                new Consent(ConsentType.EMAIL_NOTIFICATIONS, userId, true)
        );

        when(userPersistencePort.findUserById(userId)).thenReturn(Optional.of(user));
        when(consentPersistencePort.getConsentsByUserId(userId)).thenReturn(consents);

        UserResponse userResponse = userService.getUserWithCurrentConsents(userId);

        assertNotNull(userResponse);
        verify(userPersistencePort).findUserById(userId);
        verify(consentPersistencePort).getConsentsByUserId(userId);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        UUID userId = UUID.randomUUID();

        when(userPersistencePort.findUserById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserWithCurrentConsents(userId));
    }
}