package com.volkswagen.preferencecenter.infrastructure.event;

import com.volkswagen.preferencecenter.domain.event.ConsentChangedEvent;
import com.volkswagen.preferencecenter.domain.port.DomainEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggingDomainEventPublisher implements DomainEventPublisher {

    private static final Logger log = LoggerFactory.getLogger(LoggingDomainEventPublisher.class);

    @Override
    public void publish(ConsentChangedEvent consentChangedEvent) {
        log.info("Publishing event: {}", consentChangedEvent);
    }
}