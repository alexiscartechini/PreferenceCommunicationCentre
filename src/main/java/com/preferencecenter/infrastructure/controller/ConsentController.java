package com.preferencecenter.infrastructure.controller;

import com.preferencecenter.application.service.ConsentService;
import com.preferencecenter.dto.request.UpdateConsentsRequest;
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