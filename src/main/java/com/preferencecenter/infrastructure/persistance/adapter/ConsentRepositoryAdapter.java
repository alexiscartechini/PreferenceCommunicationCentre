package com.preferencecenter.infrastructure.persistance.adapter;

import com.preferencecenter.domain.model.Consent;
import com.preferencecenter.domain.port.ConsentRepositoryPort;
import com.preferencecenter.infrastructure.persistance.repository.ConsentRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ConsentRepositoryAdapter implements ConsentRepositoryPort {

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