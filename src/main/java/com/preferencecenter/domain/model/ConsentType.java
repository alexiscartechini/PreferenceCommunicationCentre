package com.preferencecenter.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ConsentType {
    EMAIL_NOTIFICATIONS,
    SMS_NOTIFICATIONS;

    @JsonCreator
    public static ConsentType from(String value) {
        return ConsentType.valueOf(value.toUpperCase());
    }
}