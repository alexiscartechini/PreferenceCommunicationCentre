package com.volkswagen.preferencecenter.domain.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Getter
@Entity
@Table(name = "consents")
public class Consent {

    @Id
    @Enumerated(EnumType.STRING)
    private ConsentType id;
    private UUID userId;
    private boolean isEnabled;

    public Consent() {
        //needed for JPA
    }

    public Consent(ConsentType id, UUID userId, boolean isEnabled) {
        this.id = id;
        this.userId = userId;
        this.isEnabled = isEnabled;
    }
}