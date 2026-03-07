package com.preferencecenter.domain.model;

import com.preferencecenter.domain.exception.InvalidEmailException;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;
    @Column(unique = true)
    private String email;

    public User() {
        //needed for JPA
    }

    public User(String email) {
        if (!isValidEmail(email)) {
            throw new InvalidEmailException(email + " is not a valid email.");
        }
        this.email = email;
    }

    public void changeEmail(String newEmail) {
        if (!isValidEmail(newEmail)) {
            throw new InvalidEmailException(newEmail + " is not a valid email.");
        }
        this.email = newEmail;
    }

    private boolean isValidEmail(String email) {
        return email.matches(".+@.+\\..+");
    }
}