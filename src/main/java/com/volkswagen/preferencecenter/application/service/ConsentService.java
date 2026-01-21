package com.volkswagen.preferencecenter.application.service;

import com.volkswagen.preferencecenter.application.exception.UserNotFoundException;
import com.volkswagen.preferencecenter.domain.model.Consent;
import com.volkswagen.preferencecenter.domain.model.ConsentChangeEvent;
import com.volkswagen.preferencecenter.domain.model.ConsentType;
import com.volkswagen.preferencecenter.domain.model.User;
import com.volkswagen.preferencecenter.domain.port.ConsentChangeEventPersistencePort;
import com.volkswagen.preferencecenter.domain.port.ConsentPersistencePort;
import com.volkswagen.preferencecenter.domain.port.UserPersistencePort;
import com.volkswagen.preferencecenter.dto.ConsentRequest;
import com.volkswagen.preferencecenter.dto.UpdateConsentsRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ConsentService {

    private final UserPersistencePort userPersistencePort;
    private final ConsentChangeEventPersistencePort consentChangeEventPersistencePort;
    private final ConsentPersistencePort consentPersistencePort;

    public ConsentService(UserPersistencePort userPersistencePort,
                          ConsentChangeEventPersistencePort consentChangeEventPersistencePort,
                          ConsentPersistencePort consentPersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.consentChangeEventPersistencePort = consentChangeEventPersistencePort;
        this.consentPersistencePort = consentPersistencePort;
    }

    public void changeConsent(UpdateConsentsRequest updateConsentsRequest) {
        User user = userPersistencePort.findUserById(updateConsentsRequest.user().id())
                .orElseThrow(() -> new UserNotFoundException("User not found."));

        updateConsentsRequest.consents().forEach(consentRequest -> {
            updateConsent(consentRequest, user);
            saveConsentChangeEvent(consentRequest, user);
            }
        );
    }

    private void updateConsent(ConsentRequest consentRequest, User user) {
        consentPersistencePort.save(new Consent(
                ConsentType.from(consentRequest.id()),
                user.getId(),
                consentRequest.enabled()
        ));
    }

    private void saveConsentChangeEvent(ConsentRequest consentRequest, User user) {
        consentChangeEventPersistencePort.save(
                new ConsentChangeEvent(
                        user.getId(),
                        ConsentType.from(consentRequest.id()),
                        consentRequest.enabled(),
                        Instant.now()
                )
        );
    }
}