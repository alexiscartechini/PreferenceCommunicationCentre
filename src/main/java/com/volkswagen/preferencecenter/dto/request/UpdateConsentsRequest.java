package com.volkswagen.preferencecenter.dto.request;

import java.util.List;

public record UpdateConsentsRequest(
        UserReference user,
        List<ConsentRequest> consents
) {
}