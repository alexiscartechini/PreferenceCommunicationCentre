package com.preferencecenter.domain.port;

import com.preferencecenter.domain.event.ConsentChangeOccurred;

public interface DomainEventPublisher {
    void publish(ConsentChangeOccurred consentChangeOccurred);
}