package com.volkswagen.preferencecenter.domain.port;

import com.volkswagen.preferencecenter.domain.model.Consent;

import java.util.List;
import java.util.UUID;

public interface ConsentPersistencePort {

    List<Consent> getConsentsByUserId(UUID id);
    void save(Consent consent);
}