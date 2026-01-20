package com.volkswagen.preferencecenter.dto;

import com.volkswagen.preferencecenter.domain.model.User;

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
}