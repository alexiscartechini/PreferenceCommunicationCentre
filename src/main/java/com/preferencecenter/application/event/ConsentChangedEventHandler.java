package com.preferencecenter.application.event;

import com.preferencecenter.domain.event.ConsentChangeOccurred;
import com.preferencecenter.domain.model.ConsentChangeEvent;
import com.preferencecenter.domain.port.ConsentChangeEventRepositoryPort;
import com.preferencecenter.domain.port.DomainEventPublisher;
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