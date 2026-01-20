package com.volkswagen.preferencecenter.domain.model;

import com.volkswagen.preferencecenter.domain.exception.InvalidEmailException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.UUID;

@Getter
@Entity
public class User {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;
    @Column(unique = true)
    private String email;

    public User() {
        //needed for JPA
    }

    public User(String email) {
        if(!isValidEmail(email)){
            throw new InvalidEmailException(email + " is not a valid email.");
        }
        this.email = email;
    }

    private boolean isValidEmail(String email) {
        return email.matches(".+@.+\\..+");
    }
}