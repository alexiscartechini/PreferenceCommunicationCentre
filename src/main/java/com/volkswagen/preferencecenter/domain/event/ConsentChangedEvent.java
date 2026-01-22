package com.volkswagen.preferencecenter.domain.event;

import com.volkswagen.preferencecenter.domain.model.ConsentChangeEvent;
import com.volkswagen.preferencecenter.domain.model.ConsentType;

import java.time.Instant;
import java.util.UUID;

public record ConsentChangedEvent(UUID userId, ConsentType consentType, boolean enabled, Instant occurredAt) {

    public static ConsentChangedEvent from(ConsentChangeEvent consentChangeEvent) {
        return new ConsentChangedEvent(
                consentChangeEvent.getUserId(),
                consentChangeEvent.getConsentType(),
                consentChangeEvent.isEnabled(),
                consentChangeEvent.getOccurredAt()
        );
    }
}