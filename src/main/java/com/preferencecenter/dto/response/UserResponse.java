package com.preferencecenter.dto.response;

import com.preferencecenter.domain.model.Consent;
import com.preferencecenter.domain.model.User;

import java.util.List;
import java.util.UUID;

public record UserResponse(UUID id, String email, List<ConsentStatusResponse> consents) {
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                List.of()
        );
    }

    public static UserResponse from(User user, List<Consent> consents) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                ConsentStatusResponse.from(consents)
        );
    }
}