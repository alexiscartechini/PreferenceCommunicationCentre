package com.volkswagen.preferencecenter.application.service;

import com.volkswagen.preferencecenter.application.exception.UserNotFoundException;
import com.volkswagen.preferencecenter.domain.event.ConsentChangedEvent;
import com.volkswagen.preferencecenter.domain.model.Consent;
import com.volkswagen.preferencecenter.domain.model.ConsentChangeEvent;
import com.volkswagen.preferencecenter.domain.model.ConsentType;
import com.volkswagen.preferencecenter.domain.model.User;
import com.volkswagen.preferencecenter.domain.port.ConsentChangeEventPersistencePort;
import com.volkswagen.preferencecenter.domain.port.ConsentPersistencePort;
import com.volkswagen.preferencecenter.domain.port.DomainEventPublisher;
import com.volkswagen.preferencecenter.domain.port.UserPersistencePort;
import com.volkswagen.preferencecenter.dto.ConsentRequest;
import com.volkswagen.preferencecenter.dto.UpdateConsentsRequest;
import com.volkswagen.preferencecenter.dto.UserReference;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ConsentServiceTest {

    private final UserPersistencePort userPersistencePort = mock(UserPersistencePort.class);
    private final ConsentChangeEventPersistencePort consentChangeEventPersistencePort = mock(ConsentChangeEventPersistencePort.class);
    private final ConsentPersistencePort consentPersistencePort = mock(ConsentPersistencePort.class);
    private final DomainEventPublisher domainEventPublisher = mock(DomainEventPublisher.class);

    private final ConsentService consentService = new ConsentService(userPersistencePort, consentChangeEventPersistencePort, consentPersistencePort, domainEventPublisher);

    @Test
    void shouldUpdateConsentForAGivenUser(){
        UUID userId = UUID.randomUUID();
        User user = new User("valid_email@email.com");
        List<ConsentRequest> consentRequests = List.of(
                new ConsentRequest(ConsentType.EMAIL_NOTIFICATIONS.name(), true),
                new ConsentRequest(ConsentType.SMS_NOTIFICATIONS.name(), true));
        UpdateConsentsRequest updateConsentsRequest = new UpdateConsentsRequest(new UserReference(userId), consentRequests);

        when(userPersistencePort.findUserById(userId)).thenReturn(Optional.of(user));

        consentService.changeConsent(updateConsentsRequest);
        verify(consentPersistencePort, times(2)).save(any(Consent.class));
        verify(consentChangeEventPersistencePort, times(2)).save(any(ConsentChangeEvent.class));
        verify(domainEventPublisher, times(2)).publish(any(ConsentChangedEvent.class));
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound(){
        UUID userId = UUID.randomUUID();
        List<ConsentRequest> consentRequests = List.of(
                new ConsentRequest(ConsentType.EMAIL_NOTIFICATIONS.name(), true),
                new ConsentRequest(ConsentType.SMS_NOTIFICATIONS.name(), true));
        UpdateConsentsRequest updateConsentsRequest = new UpdateConsentsRequest(new UserReference(userId), consentRequests);
        when(userPersistencePort.findUserById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> consentService.changeConsent(updateConsentsRequest));
        verify(consentPersistencePort, never()).save(any());
        verify(consentChangeEventPersistencePort, never()).save(any());
        verify(domainEventPublisher, never()).publish(any());
    }
}