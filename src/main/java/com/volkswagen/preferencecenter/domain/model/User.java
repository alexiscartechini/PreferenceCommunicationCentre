package com.volkswagen.preferencecenter.domain.model;

public class User {

    private String id;
    private String email;

    public boolean isValidEmail(String email) {
        return email.matches(".+@.+\\..+");
    }
}