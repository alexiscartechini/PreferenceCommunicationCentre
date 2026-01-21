package com.volkswagen.preferencecenter.domain.port;

import com.volkswagen.preferencecenter.domain.model.ConsentChangeEvent;

public interface ConsentChangeEventPersistencePort {

    void save(ConsentChangeEvent consentChangeEvent);
}