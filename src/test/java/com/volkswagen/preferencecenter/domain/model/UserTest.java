package com.volkswagen.preferencecenter.domain.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTest {

    private User user;

    @Test
    void shouldReturnTrueIfEmailIsValid(){
        user = new User("valid_email@email.com");
        assertTrue(user.isValidEmail());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "invalid_email.com",
            "invalid_email@emailcom",
            "invalid_email.com@email"
    })
    void shouldReturnFalseIfEmailIsInvalid(String invalidEmail){
        user = new User(invalidEmail);
        assertFalse(user.isValidEmail());
    }
}