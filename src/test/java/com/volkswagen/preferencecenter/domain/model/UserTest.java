package com.volkswagen.preferencecenter.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTest {

    private final User user = new User();

    @Test
    void shouldReturnTrueIfEmailIsValid(){
        assertTrue(user.isValidEmail("valid_email@email.com"));
    }

    @Test
    void shouldReturnFalseWhenEmailDoesNotContainsAtCharacter(){
        assertFalse(user.isValidEmail("invalid_email.com"));
    }

    @Test
    void shouldReturnFalseWhenEmailDoesNotContainsDotCharacter(){
        assertFalse(user.isValidEmail("invalid_email@emailcom"));
    }

    @Test
    void shouldReturnFalseWhenDotAndAtCharacterAreNotInCorrectOrderOnEmail(){
        assertFalse(user.isValidEmail("invalid_email.com@email"));
    }
}