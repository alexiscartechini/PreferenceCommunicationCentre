package com.volkswagen.preferencecenter.domain.port;

import com.volkswagen.preferencecenter.domain.model.ConsentEvent;

public interface ConsentPersistencePort {

    void save(ConsentEvent consentEvent);
}