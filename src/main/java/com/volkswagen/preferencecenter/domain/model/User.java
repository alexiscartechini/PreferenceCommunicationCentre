package com.volkswagen.preferencecenter.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    private String id;
    @Column(unique = true)
    private String email;

    public boolean isValidEmail(String email) {
        return email.matches(".+@.+\\..+");
    }
}