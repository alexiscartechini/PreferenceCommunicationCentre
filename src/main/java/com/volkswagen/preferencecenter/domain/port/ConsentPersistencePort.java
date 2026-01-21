package com.volkswagen.preferencecenter.domain.port;

import java.util.UUID;

public interface ConsentPersistencePort {

    void getConsentsByUserId(UUID id);
}