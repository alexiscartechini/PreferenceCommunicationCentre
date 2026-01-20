package com.volkswagen.preferencecenter.infrastructure.exception;

import com.volkswagen.preferencecenter.application.exception.EmailAlreadyExistsException;
import com.volkswagen.preferencecenter.application.exception.UserNotFoundException;
import com.volkswagen.preferencecenter.domain.exception.InvalidEmailException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({
            EmailAlreadyExistsException.class,
            InvalidEmailException.class
    })
    public ResponseEntity<String> handleUnprocessableEntity(RuntimeException exception){
        return ResponseEntity
                .status(422)
                .body(exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUnexistingUser(RuntimeException exception){
        return ResponseEntity
                .status(404)
                .body(exception.getMessage());
    }
}