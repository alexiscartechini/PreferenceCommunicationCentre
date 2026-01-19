package com.volkswagen.preferencecenter.infrastructure.adapter;

import com.volkswagen.preferencecenter.domain.model.ConsentEvent;
import com.volkswagen.preferencecenter.domain.port.ConsentPersistencePort;
import com.volkswagen.preferencecenter.infrastructure.repository.ConsentEventRepository;
import org.springframework.stereotype.Component;

@Component
public class ConsentEventRepositoryAdapter implements ConsentPersistencePort {

    private final ConsentEventRepository consentEventRepository;

    public ConsentEventRepositoryAdapter(ConsentEventRepository consentEventRepository) {
        this.consentEventRepository = consentEventRepository;
    }

    @Override
    public void save(ConsentEvent consentEvent) {
        consentEventRepository.save(consentEvent);
    }
}