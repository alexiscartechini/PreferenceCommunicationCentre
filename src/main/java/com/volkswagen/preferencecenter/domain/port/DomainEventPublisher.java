package com.volkswagen.preferencecenter.domain.port;

import com.volkswagen.preferencecenter.domain.event.ConsentChangeOccurred;

public interface DomainEventPublisher {
    void publish(ConsentChangeOccurred consentChangeOccurred);
}