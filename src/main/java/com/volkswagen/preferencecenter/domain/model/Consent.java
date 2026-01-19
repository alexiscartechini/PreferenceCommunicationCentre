package com.volkswagen.preferencecenter.domain.model;

public record Consent(String userId, ConsentType consentType, boolean isEnabled) {
}