package com.volkswagen.preferencecenter.infrastructure.adapter;

import com.volkswagen.preferencecenter.domain.port.ConsentPersistencePort;
import com.volkswagen.preferencecenter.infrastructure.repository.ConsentRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ConsentRepositoryAdapter implements ConsentPersistencePort {

    private final ConsentRepository consentRepository;

    public ConsentRepositoryAdapter(ConsentRepository consentRepository) {
        this.consentRepository = consentRepository;
    }

    @Override
    public void getConsentsByUserId(UUID id) {

    }
}
