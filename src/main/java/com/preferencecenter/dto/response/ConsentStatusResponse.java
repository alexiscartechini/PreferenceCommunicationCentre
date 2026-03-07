package com.preferencecenter.dto.response;

import com.preferencecenter.domain.model.Consent;

import java.util.List;

public record ConsentStatusResponse(String id, boolean isEnabled) {

    public static List<ConsentStatusResponse> from(List<Consent> consents) {
        return consents.stream()
                .map(consent ->
                        new ConsentStatusResponse(
                                consent.getConsentType().name(),
                                consent.isEnabled()
                        )
                )
                .toList();
    }
}