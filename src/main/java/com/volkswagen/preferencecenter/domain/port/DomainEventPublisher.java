package com.volkswagen.preferencecenter.domain.port;

import com.volkswagen.preferencecenter.domain.event.ConsentChangedEvent;

public interface DomainEventPublisher {
    void publish(ConsentChangedEvent consentChangedEvent);
}