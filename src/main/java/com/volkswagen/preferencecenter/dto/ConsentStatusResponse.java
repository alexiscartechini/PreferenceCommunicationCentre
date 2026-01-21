package com.volkswagen.preferencecenter.dto;

import com.volkswagen.preferencecenter.domain.model.Consent;

import java.util.List;

public record ConsentStatusResponse(String id, boolean isEnabled){

    public static List<ConsentStatusResponse> from(List<Consent> consents) {
        return consents.stream()
                .map(consent ->
                        new ConsentStatusResponse(
                                consent.getId().name(),
                                consent.isEnabled()
                        )
                )
                .toList();
    }
}