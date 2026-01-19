package com.volkswagen.preferencecenter.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTest {

    private User user;

    @Test
    void shouldReturnTrueIfEmailIsValid(){
        user = new User("valid_email@email.com");
        assertTrue(user.isValidEmail());
    }

    @Test
    void shouldReturnFalseWhenEmailDoesNotContainsAtCharacter(){
        user = new User("invalid_email.com");
        assertFalse(user.isValidEmail());
    }

    @Test
    void shouldReturnFalseWhenEmailDoesNotContainsDotCharacter(){
        user = new User("invalid_email@emailcom");
        assertFalse(user.isValidEmail());
    }

    @Test
    void shouldReturnFalseWhenDotAndAtCharacterAreNotInCorrectOrderOnEmail(){
        user = new User("invalid_email.com@email");
        assertFalse(user.isValidEmail());
    }
}