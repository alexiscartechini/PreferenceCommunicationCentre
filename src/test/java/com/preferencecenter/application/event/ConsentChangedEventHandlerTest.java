package com.preferencecenter.application.event;

import com.preferencecenter.domain.event.ConsentChangeOccurred;
import com.preferencecenter.domain.model.ConsentChangeEvent;
import com.preferencecenter.domain.model.ConsentType;
import com.preferencecenter.domain.port.ConsentChangeEventRepositoryPort;
import com.preferencecenter.domain.port.DomainEventPublisher;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ConsentChangedEventHandlerTest {

    private final ConsentChangeEventRepositoryPort repository =
            mock(ConsentChangeEventRepositoryPort.class);

    private final DomainEventPublisher publisher =
            mock(DomainEventPublisher.class);

    private final ConsentChangedEventHandler handler =
            new ConsentChangedEventHandler(repository, publisher);

    @Test
    void shouldPersistAndPublishConsentChangeEvent() {
        ConsentChangeEvent event = new ConsentChangeEvent(
                UUID.randomUUID(),
                ConsentType.EMAIL_NOTIFICATIONS,
                true,
                Instant.now()
        );

        handler.handle(event);

        verify(repository).save(event);
        verify(publisher).publish(any(ConsentChangeOccurred.class));
    }
}