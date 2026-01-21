package com.volkswagen.preferencecenter.infrastructure.controller;

import com.volkswagen.preferencecenter.dto.UpdateConsentsRequest;
import com.volkswagen.preferencecenter.application.service.ConsentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consents")
public class ConsentController {

    ConsentService consentService;

    public ConsentController(ConsentService consentService){
        this.consentService = consentService;
    }

    @PutMapping
    public void changeConsent(@RequestBody UpdateConsentsRequest updateConsentsRequest){
        consentService.changeConsent(updateConsentsRequest);
    }
}