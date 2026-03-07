package com.preferencecenter.infrastructure.event;

import com.preferencecenter.domain.event.ConsentChangeOccurred;
import com.preferencecenter.domain.port.DomainEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggingDomainEventPublisher implements DomainEventPublisher {

    private static final Logger log = LoggerFactory.getLogger(LoggingDomainEventPublisher.class);

    @Override
    public void publish(ConsentChangeOccurred consentChangeOccurred) {
        log.info("Publishing event: {}", consentChangeOccurred);
    }
}