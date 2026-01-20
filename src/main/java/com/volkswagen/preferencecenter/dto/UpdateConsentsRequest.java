package com.volkswagen.preferencecenter.dto;

import java.util.List;

public record UpdateConsentsRequest(
        UserReference user,
        List<ConsentRequest> consents
) {
}