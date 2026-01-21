package com.volkswagen.preferencecenter.infrastructure.controller;

import com.volkswagen.preferencecenter.application.service.UserService;
import com.volkswagen.preferencecenter.domain.model.User;
import com.volkswagen.preferencecenter.dto.CreateUserRequest;
import com.volkswagen.preferencecenter.dto.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest userRequest){
        User user = userService.createUser(userRequest.email());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserResponse.from(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable UUID id){
        return ResponseEntity.ok(userService.getUserWithCurrentConsents(id));
    }
}