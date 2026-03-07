package com.preferencecenter.application.service;

import com.preferencecenter.application.event.ConsentChangedEventHandler;
import com.preferencecenter.application.exception.UserNotFoundException;
import com.preferencecenter.domain.model.Consent;
import com.preferencecenter.domain.model.ConsentChangeEvent;
import com.preferencecenter.domain.model.ConsentType;
import com.preferencecenter.domain.model.User;
import com.preferencecenter.domain.port.ConsentRepositoryPort;
import com.preferencecenter.domain.port.UserRepositoryPort;
import com.preferencecenter.dto.request.ConsentRequest;
import com.preferencecenter.dto.request.UpdateConsentsRequest;
import com.preferencecenter.dto.request.UserReference;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ConsentServiceTest {

    private final UserRepositoryPort userRepositoryPort = mock(UserRepositoryPort.class);
    private final ConsentRepositoryPort consentRepositoryPort = mock(ConsentRepositoryPort.class);
    private final ConsentChangedEventHandler consentChangedEventHandler = mock(ConsentChangedEventHandler.class);

    private final ConsentService consentService = new ConsentService(userRepositoryPort, consentRepositoryPort, consentChangedEventHandler);

    @Test
    void shouldUpdateConsentsAndPublishEventsWhenUserExists() {
        UUID userId = UUID.randomUUID();
        User user = new User("valid_email@email.com");
        List<ConsentRequest> consentRequests = List.of(
                new ConsentRequest(ConsentType.EMAIL_NOTIFICATIONS.name(), true),
                new ConsentRequest(ConsentType.SMS_NOTIFICATIONS.name(), true));
        UpdateConsentsRequest updateConsentsRequest = new UpdateConsentsRequest(new UserReference(userId), consentRequests);

        when(userRepositoryPort.findUserById(userId)).thenReturn(Optional.of(user));

        consentService.changeConsent(updateConsentsRequest);
        verify(consentRepositoryPort, times(2)).save(any(Consent.class));
        verify(consentChangedEventHandler, times(2)).handle(any(ConsentChangeEvent.class));
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
        verify(consentChangedEventHandler, never()).handle(any());
    }
}