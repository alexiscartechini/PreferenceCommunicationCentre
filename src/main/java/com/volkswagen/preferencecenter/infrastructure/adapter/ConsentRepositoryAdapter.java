package com.volkswagen.preferencecenter.infrastructure.adapter;

import com.volkswagen.preferencecenter.domain.model.Consent;
import com.volkswagen.preferencecenter.domain.port.ConsentPersistencePort;
import com.volkswagen.preferencecenter.infrastructure.repository.ConsentRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ConsentRepositoryAdapter implements ConsentPersistencePort {

    private final ConsentRepository consentRepository;

    public ConsentRepositoryAdapter(ConsentRepository consentRepository) {
        this.consentRepository = consentRepository;
    }

    @Override
    public List<Consent> getConsentsByUserId(UUID id) {
        return consentRepository.findByUserId(id);
    }

    @Override
    public void save(Consent consent) {
        consentRepository.save(consent);
    }
}