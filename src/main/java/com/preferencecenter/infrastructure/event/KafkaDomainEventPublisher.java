package com.preferencecenter.infrastructure.event;

import com.preferencecenter.domain.event.ConsentChangeOccurred;
import com.preferencecenter.domain.port.DomainEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaDomainEventPublisher implements DomainEventPublisher {

    private static final String TOPIC = "consent-change-events";
    private final KafkaTemplate<String, ConsentChangeOccurred> kafkaTemplate;

    public KafkaDomainEventPublisher(KafkaTemplate<String, ConsentChangeOccurred> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(ConsentChangeOccurred consentChangeOccurred) {
        kafkaTemplate.send(TOPIC, consentChangeOccurred.userId().toString(), consentChangeOccurred);
    }
}