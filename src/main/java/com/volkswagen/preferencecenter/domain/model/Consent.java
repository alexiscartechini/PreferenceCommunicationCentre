package com.volkswagen.preferencecenter.domain.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Getter
@Entity
@Table(name = "consents")
public class Consent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ConsentType consentType;
    private UUID userId;
    private boolean isEnabled;

    public Consent() {
        //needed for JPA
    }

    public Consent(ConsentType consentType, UUID userId, boolean isEnabled) {
        this.consentType = consentType;
        this.userId = userId;
        this.isEnabled = isEnabled;
    }
}