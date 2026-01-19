package com.volkswagen.preferencecenter.application.service;

import com.volkswagen.preferencecenter.domain.model.Consent;
import com.volkswagen.preferencecenter.domain.model.ConsentEvent;
import com.volkswagen.preferencecenter.domain.model.ConsentType;
import com.volkswagen.preferencecenter.domain.model.User;
import com.volkswagen.preferencecenter.domain.port.ConsentPersistencePort;
import com.volkswagen.preferencecenter.domain.port.UserPersistencePort;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private static final String NOT_UNIQUE_EMAIL = "not_unique_email@email.com";
    private final UserPersistencePort userPersistencePort = mock(UserPersistencePort.class);
    private final ConsentPersistencePort consentPersistencePort = mock(ConsentPersistencePort.class);
    private final UserService userService = new UserService(userPersistencePort, consentPersistencePort);

    @Test
    void shouldReturnTrueIfEmailIsUnique(){
        when(userPersistencePort.findByEmail("unique_email@email.com")).thenReturn(Optional.empty());
        assertTrue(userService.isUniqueEmail("unique_email@email.com"));
    }

    @Test
    void shouldReturnFalseIfEmailIsNotUnique(){
        when(userPersistencePort.findByEmail(NOT_UNIQUE_EMAIL)).thenReturn(Optional.of(new User()));
        assertFalse(userService.isUniqueEmail(NOT_UNIQUE_EMAIL));
    }

    @Test
    void shouldCreateAConsentEventWhenUserChooseANotificationMeans(){
        userService.chooseNotification(new Consent("id", ConsentType.EMAIL_NOTIFICATIONS, true));
        verify(consentPersistencePort).save(any(ConsentEvent.class));
    }
}