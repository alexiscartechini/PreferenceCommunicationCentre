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

    public ConsentEvent(String userId, ConsentType consentType, boolean isEnabled, Instant occurredAt) {
        this.userId = userId;
        this.consentType = consentType;
        this.isEnabled = isEnabled;
        this.occurredAt = occurredAt;
    }
}