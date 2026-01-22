package com.volkswagen.preferencecenter.infrastructure.controller;

import com.volkswagen.preferencecenter.application.service.UserService;
import com.volkswagen.preferencecenter.domain.model.User;
import com.volkswagen.preferencecenter.dto.request.CreateUserRequest;
import com.volkswagen.preferencecenter.dto.response.UserResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UserControllerTest {

    private static final String VALID_EMAIL = "valid_email@email.com";
    UserService userService = mock(UserService.class);
    UserController userController = new UserController(userService);

    @Test
    void shouldCreateUser() {
        CreateUserRequest createUserRequest = new CreateUserRequest(VALID_EMAIL);
        User user = new User(VALID_EMAIL);

        when(userService.createUser(createUserRequest.email())).thenReturn(user);

        ResponseEntity<UserResponse> userResponse = userController.createUser(createUserRequest);

        assertEquals(HttpStatus.CREATED, userResponse.getStatusCode());
        assertNotNull(userResponse.getBody());
        assertEquals(VALID_EMAIL, userResponse.getBody().email());

        verify(userService).createUser(createUserRequest.email());
    }

    @Test
    void shouldGetUserById() {
        UUID userId = UUID.randomUUID();
        UserResponse userResponse = new UserResponse(userId, VALID_EMAIL, List.of());
        when(userService.getUserWithCurrentConsents(userId)).thenReturn(userResponse);

        ResponseEntity<UserResponse> result = userController.getUser(userId);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(userResponse, result.getBody());
        verify(userService).getUserWithCurrentConsents(userId);
    }
}