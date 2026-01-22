package com.volkswagen.preferencecenter.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ConsentRequest(@JsonProperty("id") String consentType, boolean enabled) {
}