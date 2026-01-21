package com.volkswagen.preferencecenter.infrastructure.persistance.adapter;

import com.volkswagen.preferencecenter.domain.model.ConsentChangeEvent;
import com.volkswagen.preferencecenter.domain.port.ConsentChangeEventPersistencePort;
import com.volkswagen.preferencecenter.infrastructure.persistance.repository.ConsentChangeEventRepository;
import org.springframework.stereotype.Component;

@Component
public class ConsentChangeEventRepositoryAdapter implements ConsentChangeEventPersistencePort {

    private final ConsentChangeEventRepository consentChangeEventRepository;

    public ConsentChangeEventRepositoryAdapter(ConsentChangeEventRepository consentChangeEventRepository) {
        this.consentChangeEventRepository = consentChangeEventRepository;
    }

    @Override
    public void save(ConsentChangeEvent consentChangeEvent) {
        consentChangeEventRepository.save(consentChangeEvent);
    }
}