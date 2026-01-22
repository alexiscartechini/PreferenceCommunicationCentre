package com.volkswagen.preferencecenter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ConsentRequest(@JsonProperty("id") String consentType, boolean enabled) {
}