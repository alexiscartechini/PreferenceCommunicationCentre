package com.volkswagen.preferencecenter.infrastructure.controller;

import com.volkswagen.preferencecenter.application.service.ConsentService;
import com.volkswagen.preferencecenter.dto.UpdateConsentsRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consents")
public class ConsentController {

    private final ConsentService consentService;

    public ConsentController(ConsentService consentService) {
        this.consentService = consentService;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeConsent(@RequestBody UpdateConsentsRequest updateConsentsRequest) {
        consentService.changeConsent(updateConsentsRequest);
    }
}