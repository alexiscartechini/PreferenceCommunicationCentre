package com.volkswagen.preferencecenter.domain.port;

import com.volkswagen.preferencecenter.domain.model.ConsentChangeEvent;

public interface ConsentChangeEventRepositoryPort {

    void save(ConsentChangeEvent consentChangeEvent);
}