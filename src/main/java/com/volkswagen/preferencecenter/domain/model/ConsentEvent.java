package com.volkswagen.preferencecenter.domain.model;

import jakarta.persistence.*;

import java.time.Instant;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class ConsentEvent {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private final String userId;
    @Enumerated(EnumType.STRING)
    private final ConsentType consentType;
    private final boolean isEnabled;
    private final Instant occurredAt;

    public ConsentEvent(Consent consent, Instant occurredAt) {
        this.userId = consent.userId();
        this.consentType = consent.consentType();
        this.isEnabled = consent.isEnabled();
        this.occurredAt = occurredAt;
    }
}