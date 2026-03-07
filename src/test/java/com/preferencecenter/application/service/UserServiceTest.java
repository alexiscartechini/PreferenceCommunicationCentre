package com.preferencecenter.application.service;

import com.preferencecenter.application.exception.EmailAlreadyExistsException;
import com.preferencecenter.application.exception.UserNotFoundException;
import com.preferencecenter.domain.model.Consent;
import com.preferencecenter.domain.model.ConsentType;
import com.preferencecenter.domain.model.User;
import com.preferencecenter.domain.port.ConsentRepositoryPort;
import com.preferencecenter.domain.port.UserRepositoryPort;
import com.preferencecenter.dto.response.UserResponse;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private static final String NOT_UNIQUE_EMAIL = "not_unique_email@email.com";
    private static final String UNIQUE_EMAIL = "unique_email@email.com";
    private final UserRepositoryPort userRepositoryPort = mock(UserRepositoryPort.class);
    private final ConsentRepositoryPort consentRepositoryPort = mock(ConsentRepositoryPort.class);
    private final UserService userService = new UserService(userRepositoryPort, consentRepositoryPort);

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {
        when(userRepositoryPort.findByEmail(NOT_UNIQUE_EMAIL)).thenReturn(Optional.of(new User(NOT_UNIQUE_EMAIL)));

        assertFalse(userService.isUniqueEmail(NOT_UNIQUE_EMAIL));
        assertThrows(EmailAlreadyExistsException.class, () -> userService.createUser(NOT_UNIQUE_EMAIL));
        verify(userRepositoryPort, never()).save(any());
    }

    @Test
    void shouldCreateUser() {
        when(userRepositoryPort.findByEmail(UNIQUE_EMAIL)).thenReturn(Optional.empty());

        User result = userService.createUser(UNIQUE_EMAIL);

        assertTrue(userService.isUniqueEmail(UNIQUE_EMAIL));
        assertNotNull(result);
        verify(userRepositoryPort).save(any(User.class));
    }

    @Test
    void shouldReturnUserWithConsents() {
        UUID userId = UUID.randomUUID();
        User user = new User(UNIQUE_EMAIL);

        List<Consent> consents = List.of(
                new Consent(ConsentType.SMS_NOTIFICATIONS, userId, true),
                new Consent(ConsentType.EMAIL_NOTIFICATIONS, userId, true)
        );

        when(userRepositoryPort.findUserById(userId)).thenReturn(Optional.of(user));
        when(consentRepositoryPort.getConsentsByUserId(userId)).thenReturn(consents);

        UserResponse userResponse = userService.getUserWithCurrentConsents(userId);

        assertNotNull(userResponse);
        verify(userRepositoryPort).findUserById(userId);
        verify(consentRepositoryPort).getConsentsByUserId(userId);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        UUID userId = UUID.randomUUID();

        when(userRepositoryPort.findUserById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserWithCurrentConsents(userId));
    }

    @Test
    void shouldUpdateEmail() {
        UUID userId = UUID.randomUUID();
        User user = new User(UNIQUE_EMAIL);

        when(userRepositoryPort.findUserById(userId)).thenReturn(Optional.of(user));

        String newEmail = "new_email@email.com";
        userService.updateEmail(userId, newEmail);

        assertEquals(newEmail, user.getEmail());
        verify(userRepositoryPort).findUserById(userId);
    }

    @Test
    void shouldDeleteUser() {
        UUID userId = UUID.randomUUID();

        userService.deleteUser(userId);

        verify(userRepositoryPort).deleteById(userId);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingEmailAndUserNotFound() {
        UUID userId = UUID.randomUUID();

        when(userRepositoryPort.findUserById(userId)).thenReturn(Optional.empty());

        assertThrows(
                UserNotFoundException.class,
                () -> userService.updateEmail(userId, "new@email.com")
        );

        verify(userRepositoryPort).findUserById(userId);
    }
}