package com.preferencecenter.infrastructure.controller;

import com.preferencecenter.application.service.UserService;
import com.preferencecenter.domain.model.User;
import com.preferencecenter.dto.request.CreateUserRequest;
import com.preferencecenter.dto.request.UpdateUserEmailRequest;
import com.preferencecenter.dto.response.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest userRequest) {
        User user = userService.createUser(userRequest.email());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserResponse.from(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUserWithCurrentConsents(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEmail(@PathVariable UUID id, @RequestBody UpdateUserEmailRequest updateUserEmailRequest) {
        userService.updateEmail(id, updateUserEmailRequest.email());
    }
}