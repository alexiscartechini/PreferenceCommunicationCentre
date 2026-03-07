package com.preferencecenter.application.service;

import com.preferencecenter.application.event.ConsentChangedEventHandler;
import com.preferencecenter.application.exception.UserNotFoundException;
import com.preferencecenter.domain.model.Consent;
import com.preferencecenter.domain.model.ConsentChangeEvent;
import com.preferencecenter.domain.model.ConsentType;
import com.preferencecenter.domain.model.User;
import com.preferencecenter.domain.port.ConsentRepositoryPort;
import com.preferencecenter.domain.port.UserRepositoryPort;
import com.preferencecenter.dto.request.ConsentRequest;
import com.preferencecenter.dto.request.UpdateConsentsRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class ConsentService {

    private final UserRepositoryPort userRepositoryPort;
    private final ConsentRepositoryPort consentRepositoryPort;
    private final ConsentChangedEventHandler consentChangedEventHandler;

    public ConsentService(UserRepositoryPort userRepositoryPort,
                          ConsentRepositoryPort consentRepositoryPort, ConsentChangedEventHandler consentChangedEventHandler) {
        this.userRepositoryPort = userRepositoryPort;
        this.consentRepositoryPort = consentRepositoryPort;
        this.consentChangedEventHandler = consentChangedEventHandler;
    }

    private ConsentChangeEvent toConsentChangeEvent(ConsentRequest consentRequest, User user) {
        return new ConsentChangeEvent(
                user.getId(),
                ConsentType.from(consentRequest.consentType()),
                consentRequest.enabled(),
                Instant.now()
        );
    }

    @Transactional
    public void changeConsent(UpdateConsentsRequest updateConsentsRequest) {
        User user = userRepositoryPort.findUserById(updateConsentsRequest.user().id())
                .orElseThrow(() -> new UserNotFoundException("User not found."));

        updateConsentsRequest.consents().forEach(consentRequest -> {
                    updateConsent(consentRequest, user);
                    consentChangedEventHandler.handle(toConsentChangeEvent(consentRequest, user));
                }
        );
    }

    private void updateConsent(ConsentRequest consentRequest, User user) {
        consentRepositoryPort.save(new Consent(
                ConsentType.from(consentRequest.consentType()),
                user.getId(),
                consentRequest.enabled()
        ));
    }
}