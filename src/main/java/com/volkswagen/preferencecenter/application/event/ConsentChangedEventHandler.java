package com.volkswagen.preferencecenter.application.event;

import com.volkswagen.preferencecenter.domain.event.ConsentChangeOccurred;
import com.volkswagen.preferencecenter.domain.model.ConsentChangeEvent;
import com.volkswagen.preferencecenter.domain.port.ConsentChangeEventRepositoryPort;
import com.volkswagen.preferencecenter.domain.port.DomainEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ConsentChangedEventHandler {

    private final ConsentChangeEventRepositoryPort consentChangeEventRepositoryPort;
    private final DomainEventPublisher domainEventPublisher;

    public ConsentChangedEventHandler(
            ConsentChangeEventRepositoryPort consentChangeEventRepositoryPort,
            DomainEventPublisher domainEventPublisher
    ) {
        this.consentChangeEventRepositoryPort = consentChangeEventRepositoryPort;
        this.domainEventPublisher = domainEventPublisher;
    }

    public void handle(ConsentChangeEvent event) {
        consentChangeEventRepositoryPort.save(event);
        domainEventPublisher.publish(ConsentChangeOccurred.from(event));
    }
}