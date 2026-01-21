package com.volkswagen.preferencecenter.domain.port;

import com.volkswagen.preferencecenter.domain.model.ConsentEvent;

public interface ConsentChangeEventPersistencePort {

    void save(ConsentEvent consentEvent);
}