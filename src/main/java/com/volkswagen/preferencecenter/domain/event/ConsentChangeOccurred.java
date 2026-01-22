package com.volkswagen.preferencecenter.domain.event;

import com.volkswagen.preferencecenter.domain.model.ConsentChangeEvent;
import com.volkswagen.preferencecenter.domain.model.ConsentType;

import java.time.Instant;
import java.util.UUID;

public record ConsentChangeOccurred(UUID userId, ConsentType consentType, boolean enabled, Instant occurredAt) {

    public static ConsentChangeOccurred from(ConsentChangeEvent consentChangeEvent) {
        return new ConsentChangeOccurred(
                consentChangeEvent.getUserId(),
                consentChangeEvent.getConsentType(),
                consentChangeEvent.isEnabled(),
                consentChangeEvent.getOccurredAt()
        );
    }
}