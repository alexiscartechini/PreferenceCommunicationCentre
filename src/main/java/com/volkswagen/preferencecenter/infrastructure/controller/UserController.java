package com.volkswagen.preferencecenter.infrastructure.controller;

import com.volkswagen.preferencecenter.application.service.UserService;
import com.volkswagen.preferencecenter.domain.model.User;
import com.volkswagen.preferencecenter.dto.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody String email){
        User user = userService.createUser(email);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserResponse.from(user));
    }
}