package com.volkswagen.preferencecenter.application.service;

import com.volkswagen.preferencecenter.application.exception.UserNotFoundException;
import com.volkswagen.preferencecenter.domain.model.ConsentEvent;
import com.volkswagen.preferencecenter.domain.model.ConsentType;
import com.volkswagen.preferencecenter.domain.model.User;
import com.volkswagen.preferencecenter.domain.port.ConsentPersistencePort;
import com.volkswagen.preferencecenter.domain.port.UserPersistencePort;
import com.volkswagen.preferencecenter.dto.UpdateConsentsRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ConsentService {

    private final UserPersistencePort userPersistencePort;
    private final ConsentPersistencePort consentPersistencePort;

    public ConsentService(UserPersistencePort userPersistencePort, ConsentPersistencePort consentPersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.consentPersistencePort = consentPersistencePort;
    }

    public void chooseConsent(UpdateConsentsRequest updateConsentsRequest) {
        User user = userPersistencePort.findUserById(updateConsentsRequest.user().id())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        updateConsentsRequest.consents().forEach(consentRequest ->
                consentPersistencePort.save(
                        new ConsentEvent(
                                user.getId(),
                                ConsentType.from(consentRequest.id()),
                                consentRequest.enabled(),
                                Instant.now()
                        )
                ));
    }
}