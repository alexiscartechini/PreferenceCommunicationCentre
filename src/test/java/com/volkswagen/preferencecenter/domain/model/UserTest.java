package com.volkswagen.preferencecenter.domain.model;

import com.volkswagen.preferencecenter.domain.exception.InvalidEmailException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserTest {

    private static final String VALID_EMAIL = "valid_email@email.com";

    @Test
    void shouldReturnTrueIfEmailIsValid() {
        User user = new User(VALID_EMAIL);
        assertEquals(VALID_EMAIL, user.getEmail());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "invalid_email.com",
            "invalid_email@emailcom",
            "invalid_email.com@email"
    })
    void shouldReturnFalseIfEmailIsInvalid(String invalidEmail) {
        assertThrows(InvalidEmailException.class, () -> new User(invalidEmail));
    }
}