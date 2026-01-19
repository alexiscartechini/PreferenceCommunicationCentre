package com.volkswagen.preferencecenter.dto;

import java.util.List;

public record UpdateConsentsRequest(
        UserReference userReference,
        List<ConsentRequest> consents
) {
}