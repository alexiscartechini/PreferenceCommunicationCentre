package com.preferencecenter.domain.port;

import com.preferencecenter.domain.model.ConsentChangeEvent;

public interface ConsentChangeEventRepositoryPort {

    void save(ConsentChangeEvent consentChangeEvent);
}