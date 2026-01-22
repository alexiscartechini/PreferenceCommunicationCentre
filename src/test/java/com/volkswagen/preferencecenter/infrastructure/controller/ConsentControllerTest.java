package com.volkswagen.preferencecenter.infrastructure.controller;

import com.volkswagen.preferencecenter.application.service.ConsentService;
import com.volkswagen.preferencecenter.dto.request.UpdateConsentsRequest;
import com.volkswagen.preferencecenter.dto.request.UserReference;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ConsentControllerTest {

    ConsentService consentService = mock(ConsentService.class);
    ConsentController consentController = new ConsentController(consentService);

    @Test
    void shouldChangeConsent() {
        UUID userId = UUID.randomUUID();
        UpdateConsentsRequest updateConsentsRequest =
                new UpdateConsentsRequest(new UserReference(userId), List.of());

        consentController.changeConsent(updateConsentsRequest);

        verify(consentService).changeConsent(updateConsentsRequest);
    }
}