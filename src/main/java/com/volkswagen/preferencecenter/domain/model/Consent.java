package com.volkswagen.preferencecenter.domain.model;

public class Consent {

    private String userId;
    private ConsentType consentType;
    private boolean isEnabled;

    public Consent(String userId, ConsentType consentType, boolean isEnabled) {
        this.userId = userId;
        this.consentType = consentType;
        this.isEnabled = isEnabled;
    }
}