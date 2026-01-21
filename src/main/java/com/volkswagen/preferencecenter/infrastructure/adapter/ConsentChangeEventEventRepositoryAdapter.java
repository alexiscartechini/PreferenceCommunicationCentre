package com.volkswagen.preferencecenter.infrastructure.adapter;

import com.volkswagen.preferencecenter.domain.model.ConsentEvent;
import com.volkswagen.preferencecenter.domain.port.ConsentChangeEventPersistencePort;
import com.volkswagen.preferencecenter.infrastructure.repository.ConsentEventRepository;
import org.springframework.stereotype.Component;

@Component
public class ConsentChangeEventEventRepositoryAdapter implements ConsentChangeEventPersistencePort {

    private final ConsentEventRepository consentEventRepository;

    public ConsentChangeEventEventRepositoryAdapter(ConsentEventRepository consentEventRepository) {
        this.consentEventRepository = consentEventRepository;
    }

    @Override
    public void save(ConsentEvent consentEvent) {
        consentEventRepository.save(consentEvent);
    }
}