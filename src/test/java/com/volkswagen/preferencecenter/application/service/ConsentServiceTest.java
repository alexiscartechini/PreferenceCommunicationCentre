package com.volkswagen.preferencecenter.application.service;

import com.volkswagen.preferencecenter.application.exception.UserNotFoundException;
import com.volkswagen.preferencecenter.domain.event.ConsentChangedEvent;
import com.volkswagen.preferencecenter.domain.model.Consent;
import com.volkswagen.preferencecenter.domain.model.ConsentChangeEvent;
import com.volkswagen.preferencecenter.domain.model.ConsentType;
import com.volkswagen.preferencecenter.domain.model.User;
import com.volkswagen.preferencecenter.domain.port.ConsentChangeEventRepositoryPort;
import com.volkswagen.preferencecenter.domain.port.ConsentRepositoryPort;
import com.volkswagen.preferencecenter.domain.port.DomainEventPublisher;
import com.volkswagen.preferencecenter.domain.port.UserRepositoryPort;
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

    private final UserRepositoryPort userRepositoryPort = mock(UserRepositoryPort.class);
    private final ConsentChangeEventRepositoryPort consentChangeEventRepositoryPort = mock(ConsentChangeEventRepositoryPort.class);
    private final ConsentRepositoryPort consentRepositoryPort = mock(ConsentRepositoryPort.class);
    private final DomainEventPublisher domainEventPublisher = mock(DomainEventPublisher.class);

    private final ConsentService consentService = new ConsentService(userRepositoryPort, consentChangeEventRepositoryPort, consentRepositoryPort, domainEventPublisher);

    @Test
    void shouldUpdateConsentForAGivenUser() {
        UUID userId = UUID.randomUUID();
        User user = new User("valid_email@email.com");
        List<ConsentRequest> consentRequests = List.of(
                new ConsentRequest(ConsentType.EMAIL_NOTIFICATIONS.name(), true),
                new ConsentRequest(ConsentType.SMS_NOTIFICATIONS.name(), true));
        UpdateConsentsRequest updateConsentsRequest = new UpdateConsentsRequest(new UserReference(userId), consentRequests);

        when(userRepositoryPort.findUserById(userId)).thenReturn(Optional.of(user));

        consentService.changeConsent(updateConsentsRequest);
        verify(consentRepositoryPort, times(2)).save(any(Consent.class));
        verify(consentChangeEventRepositoryPort, times(2)).save(any(ConsentChangeEvent.class));
        verify(domainEventPublisher, times(2)).publish(any(ConsentChangedEvent.class));
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        UUID userId = UUID.randomUUID();
        List<ConsentRequest> consentRequests = List.of(
                new ConsentRequest(ConsentType.EMAIL_NOTIFICATIONS.name(), true),
                new ConsentRequest(ConsentType.SMS_NOTIFICATIONS.name(), true));
        UpdateConsentsRequest updateConsentsRequest = new UpdateConsentsRequest(new UserReference(userId), consentRequests);
        when(userRepositoryPort.findUserById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> consentService.changeConsent(updateConsentsRequest));
        verify(consentRepositoryPort, never()).save(any());
        verify(consentChangeEventRepositoryPort, never()).save(any());
        verify(domainEventPublisher, never()).publish(any());
    }
}