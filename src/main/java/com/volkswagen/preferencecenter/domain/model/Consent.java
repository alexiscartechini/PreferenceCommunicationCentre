package com.volkswagen.preferencecenter.domain.model;

import jakarta.persistence.*;

import java.util.UUID;

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
}
