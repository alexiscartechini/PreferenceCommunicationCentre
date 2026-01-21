package com.volkswagen.preferencecenter.domain.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "consent_change_events")
public class ConsentChangeEvent {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private UUID userId;
    @Enumerated(EnumType.STRING)
    private ConsentType consentType;
    private boolean isEnabled;
    private Instant occurredAt;

    public ConsentChangeEvent() {
        //needed for JPA
    }

    public ConsentChangeEvent(UUID userId, ConsentType consentType, boolean isEnabled, Instant occurredAt) {
        this.userId = userId;
        this.consentType = consentType;
        this.isEnabled = isEnabled;
        this.occurredAt = occurredAt;
    }
}