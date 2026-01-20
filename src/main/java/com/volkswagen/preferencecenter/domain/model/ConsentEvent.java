package com.volkswagen.preferencecenter.domain.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "consent_events")
public class ConsentEvent {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private UUID userId;
    @Enumerated(EnumType.STRING)
    private ConsentType consentType;
    private boolean isEnabled;
    private Instant occurredAt;

    public ConsentEvent() {
        //needed for JPA
    }

    public ConsentEvent(UUID userId, ConsentType consentType, boolean isEnabled, Instant occurredAt) {
        this.userId = userId;
        this.consentType = consentType;
        this.isEnabled = isEnabled;
        this.occurredAt = occurredAt;
    }
}