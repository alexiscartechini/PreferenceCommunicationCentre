package com.preferencecenter.domain.port;

import com.preferencecenter.domain.model.Consent;

import java.util.List;
import java.util.UUID;

public interface ConsentRepositoryPort {

    List<Consent> getConsentsByUserId(UUID id);

    void save(Consent consent);
}