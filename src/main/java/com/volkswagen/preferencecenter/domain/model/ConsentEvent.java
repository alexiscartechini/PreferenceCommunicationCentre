package com.volkswagen.preferencecenter.domain.model;

import jakarta.persistence.*;

import java.time.Instant;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class ConsentEvent {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String userId;
    @Enumerated(EnumType.STRING)
    private ConsentType consentType;
    private boolean isEnabled;
    private Instant occurredAt;

    public ConsentEvent() {
        //needed for JPA
    }

    public ConsentEvent(Consent consent, Instant occurredAt) {
        this.userId = consent.userId();
        this.consentType = consent.consentType();
        this.isEnabled = consent.isEnabled();
        this.occurredAt = occurredAt;
    }
}